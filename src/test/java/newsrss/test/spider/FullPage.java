package newsrss.test.spider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import newsrss.common.RSSConstants;
import newsrss.dao.Article;
import newsrss.dao.InterXML;
import newsrss.spider.LevelLimitScheduler;
import newsrss.utils.DateUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.UrlUtils;

public class FullPage implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(200);
//	private String risnews = ".*/[\\d]+/[\\d]+/[\\d]+\\.html";
//	private String xlist = "ul[@class='text_list_f14']//a//@href";
//	private String xnext = "p[@id='pages']";
//	private String rnext = ".*<a href=\"([a-z/\\d\\.]+)\">下一页</a>";
//	private String xtitle = "div[@id='content_head']//h1";
//	private String rtitle = "<h1>(.*)</h1>";
//	private String xcontent = "div[@id='endtext']";
//	private String rcontent = "(.*)";
//	private String xtime = "div[@id='content']//h2";
//	private String rtime = ".*发布时间：<span>([\\d-]+)</span>.*";
//	private String rnewid = ".*/([\\d]+/[\\d]+/[\\d]+)\\.html";
	
	private String risnews = ".*_\\.html";
	private String xlist = "ul[@class='text_list_f14']//a//@href";
	private String xnext = "p[@id='pages']";
	private String rnext = ".*<a href=\"([a-z/\\d\\.]+)\">下一页</a>";
	private String xtitle = "//article//h1/text()";
	private String rtitle = "(.*)";
	private String xcontent = "//article";
	private String rcontent = "<article .*</h1>(.*)</article>";
	private String xtime = "//article//div[@class='articletime']//text()";
	private String rtime = "(.*)　　.*";
	private String rnewid = ".*/(.*)_\\.html";
	
	@Override
	public void process(Page page) {
		Selectable url = page.getUrl();
		if(page.getHtml().xpath("base").match()){
			page.setUrl(page.getHtml().xpath("//base/@href"));
		}
		if(url.toString().matches(risnews)){
			String tmp = page.getHtml().xpath(xtitle).regex(rtitle).toString();
			System.out.println(tmp);
			List<String> srcs = page.getHtml().xpath(xcontent).xpath("//img/@src").all();
			tmp = page.getHtml().xpath(xcontent).regex(rcontent).toString();
			for(String src : srcs){
				tmp = tmp.replace(src, UrlUtils.canonicalizeUrl(src, page.getUrl().toString()));
			}
			tmp = page.getHtml().xpath(xtime).regex(rtime).replace("[年月/]", "-").replace("[日]","").toString();
			System.out.println(tmp);
			System.out.println(DateUtil.Date2TimeStamp(tmp));
			tmp = url.regex(rnewid).toString();
			System.out.println(tmp);
		}else{
			String baseUrl = page.getUrl().toString();
			page.addTargetRequests(page.getHtml().xpath(xlist).all());
			String next = page.getHtml().xpath(xnext).regex(rnext).toString();
			next = UrlUtils.canonicalizeUrl(next, baseUrl);
			Request nextRequest = new Request(next);
			Map<String, Object> extras = new HashMap<String, Object>();
			extras.put("_level",(Integer)page.getRequest().getExtra("_level")+1);
			nextRequest.setExtras(extras);
			page.addTargetRequest(nextRequest);
		}		
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String args[]) {
		String url = "http://www.tsinghua.edu.cn/publish/thunews/9649/2018/20180410135451430565458/20180410135451430565458_.html";
		Map<String, Object> extras = new HashMap<String, Object>();
		extras.put("_level",0);
		Request request = new Request(url);
		request.setExtras(extras);
		
		Spider.create(new FullPage()).addRequest(request)
			.setScheduler(new LevelLimitScheduler(RSSConstants.NEWS_DEEP))
			.thread(3).start();
//		String str = "2018年04月10日 15:07:29";
//		str = str.replaceAll("[年月/]", "-").replaceAll("[日]","");
//		System.out.println(DateUtil.Date2TimeStamp(str));
	}
}
