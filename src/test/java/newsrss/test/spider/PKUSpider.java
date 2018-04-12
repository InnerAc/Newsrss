package newsrss.test.spider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import newsrss.spider.LevelLimitScheduler;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.selector.Selector;
import us.codecraft.webmagic.utils.UrlUtils;

public class PKUSpider implements PageProcessor{
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
	
	@Override
	public void process(Page page) {
		
//		String next = page.getHtml().xpath("//div[@width='100%']").regex(".*<a href=\"(xwzh_[\\d]+\\.htm)\" class=\"be12\">下一页</a>.*").toString();
//		next = "../"+next;
//		next = UrlUtils.canonicalizeUrl(next, page.getUrl().toString());
//		System.out.println(next);
		Selectable content = page.getHtml().xpath("//td[@class='con14']");
		String contentStr = content.toString();
		List<String> srcs = content.xpath("//img/@src").all();
		for(String src : srcs){
			contentStr = contentStr.replace(src, UrlUtils.canonicalizeUrl(src, page.getUrl().toString()));
		}
//		content.replaceAll("src=\"(.*)\"", "aaaa=$0");
		System.out.println(contentStr);
		
	}

	@Override
	public Site getSite() {
		return site;
	}
	public static void main(String args[]){
//		String url = "http://pkunews.pku.edu.cn/xwzh/xwzh.htm";
		String url = "http://pkunews.pku.edu.cn/xwzh/2018-04/01/content_301685.htm";
		Spider.create(new PKUSpider()).addUrl(url).thread(5).start();
	}
}
