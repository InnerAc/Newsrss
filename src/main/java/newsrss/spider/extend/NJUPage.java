package newsrss.spider.extend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import newsrss.spider.TemplatePage;
import newsrss.utils.DateUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.HttpConstant;
import us.codecraft.webmagic.utils.UrlUtils;

public class NJUPage extends TemplatePage{

	@Override
	public void process(Page page) {
		Selectable url = page.getUrl();
		System.out.println("--->"+url);
		if(url.toString().matches(".*show_article.*")){
			page.putField("asrc", url.toString());
			page.putField("auid", uid);
			String tmp = page.getHtml().xpath("//div[@class='article_title']").regex("<div class=\"article_title\">(.*)</div>").toString();
			page.putField("atitle", tmp);
			List<String> srcs = page.getHtml().xpath("//div[@class='article_content']").xpath("//img/@src").all();
			tmp = page.getHtml().xpath("//div[@class='article_content']").toString();
			for(String src : srcs){
				tmp = tmp.replace(src, UrlUtils.canonicalizeUrl(src, page.getUrl().toString()));
			}
			page.putField("acontent", tmp);
			tmp = page.getHtml().xpath("//div[@class='article_info']").regex("发布时间：\\[([\\d-]+)\\]").toString();
			page.putField("atime", DateUtil.HanDate2TimeStamp(tmp));
			tmp = url.regex(".*/show_article_([\\d_]+)").toString();
			page.putField("akey", tmp);
		}else{
			//获取爬取深度
			int deep = (Integer)page.getRequest().getExtra("_level")+1;
			boolean isFirstPage = false;
			isFirstPage = url.toString().equals("http://news.nju.edu.cn/list_1.html");
			System.out.println("isFirstPage = "+isFirstPage);
			//因为是AJAX请求，所以对于第一个页面和其他页面有不同的解析式
			if(deep == 1){
				page.addTargetRequests(page.getHtml().xpath("//div[@id='cat_list_content']//a//@href").all());
				if(isFirstPage){	//当为起始页时
					//添加科技动态页面
					Map<String, Object> extras = new HashMap<String, Object>();
					extras.put("_level",0);
					Request tab = new Request("http://news.nju.edu.cn/list_12.html");
					tab.setExtras(extras);
					page.addTargetRequest(tab);	
				}
				
			}else{
				page.addTargetRequests(page.getHtml().xpath("//ul//a/@href").all());
			}
			if(isFull && isFirstPage){
			//记录爬取深度
				Map<String, Object> extras = new HashMap<String, Object>();
				extras.put("_level",deep);
				
				Map<String, Object> params = new HashMap<String, Object>();
				Request next = new Request("http://news.nju.edu.cn/catlist.html");
				next.setExtras(extras);
				//设置请求方式
				next.setMethod(HttpConstant.Method.POST);
				//下一页请求，构建POST请求字段
				params.put("rstr", "1,"+(deep+1)+",20");	
				next.setRequestBody(HttpRequestBody.form(params, "utf-8"));
				page.addTargetRequest(next);
				
				//请求科技动态页面的下一页
				extras = new HashMap<String, Object>();
				extras.put("_level",deep);
				
				params = new HashMap<String, Object>();
				next = new Request("http://news.nju.edu.cn/catlist.html");
				next.setExtras(extras);
				//设置请求方式
				next.setMethod(HttpConstant.Method.POST);
				//下一页请求，构建POST请求字段
				params.put("rstr", "12,"+(deep+1)+",20");	
				next.setRequestBody(HttpRequestBody.form(params, "utf-8"));
				page.addTargetRequest(next);
				
			}
		}
		
	}

	@Override
	public List<String> getStartUrl(int number) {
		List<String> urls = new ArrayList<>();
		urls.add("http://news.nju.edu.cn/list_1.html");
		return urls;
	}
	
	public static void main(String[] args){
		String url = "http://news.nju.edu.cn/list_1.html";
		Request urlRequest = new Request(url);
		Map<String, Object> extras = new HashMap<String, Object>();
		extras.put("_level",0);
		urlRequest.setExtras(extras);
		Spider.create(new NJUPage()).addRequest(urlRequest).thread(5).start();
	}

}
