package newsrss.spider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

public class TestSpider implements PageProcessor{

	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	private int key = 0;
	
	public TestSpider setKey(int key){
		this.key = key;
		return this;
	}
	
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		if(page.getUrl().toString().matches(".*_\\.html")){
			String rootUrl = page.getUrl().regex("(http://[a-zA-z\\d\\.]+/)").toString();
			String tmp = page.getHtml().xpath("//article//h1/text()").toString();
			System.out.println(key+"---"+tmp+" "+page.getUrl());
			tmp = page.getHtml().xpath("//article").regex("<article .*</h1>(.*)</article>").replace("src=\"/", "src=\""+rootUrl).toString();
//			System.out.println(tmp);
			tmp = page.getHtml().xpath("//article//div[@class='articletime']//text()").regex("(.*)　　.*").replace("[年月/]", "-").replace("[日]","").toString();
//			System.out.println(tmp);
			tmp = page.getUrl().regex(".*/(.*)_\\.html").toString();
//			System.out.println(tmp);
		}else{
			String rootUrl = page.getUrl().regex("(http://[a-zA-z\\d\\.]+)").toString();
			page.addTargetRequests(page.getHtml().xpath("//a[@class='jiequ']/@href").all());
			String next = page.getHtml().xpath("//span[@style='font-size:12px']").regex(".*<a href=\"(/publish/thunews/9649/index_[\\d]+\\.html)\"><font color=\"#000000\">下一页</font>.*").toString();
			if(next.startsWith("?") || next.startsWith("/")){
				next = rootUrl+next;
			}
			Request nextRequest = new Request(next);
			Map<String, Object> extras = new HashMap<String, Object>();
			extras.put("_level",(Integer)page.getRequest().getExtra("_level")+1);
			System.out.println(extras.get("_level"));
			nextRequest.setExtras(extras);
			System.out.println(nextRequest);
			page.addTargetRequest(nextRequest);
		}
		
	}
	
	public static void main(String args[]){
		String url = "http://www.tsinghua.edu.cn/publish/thunews/9649/index.html";
//		String url = "http://www.tsinghua.edu.cn/publish/thunews/10303/2018/20180330100227758137234/20180330100227758137234_.html";
		Map<String, Object> extras = new HashMap<String, Object>();
		extras.put("_level",0);
		Request request = new Request(url);
		request.setExtras(extras);
		Spider.create(new TestSpider()).addRequest(request).setScheduler(new LevelLimitScheduler(3)).thread(5).run();
	}

}
