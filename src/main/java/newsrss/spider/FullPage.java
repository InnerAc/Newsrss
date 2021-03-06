package newsrss.spider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;

import newsrss.dao.Article;
import newsrss.dao.InterXML;
import newsrss.utils.DateUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.UrlUtils;

public class FullPage implements PageProcessor {
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	private String xurl = null;
	private int uid = 0;
	private String risnews = null;
	private String xlist = null;
	private String rlist = null;
	private String xnext = null;
	private String rnext = null;
	private String xtitle = null;
	private String rtitle = null;
	private String xcontent = null;
	private String rcontent = null;
	private String xtime = null;
	private String rtime = null;
	private String xnewid = null;
	private String rnewid = null;
	
	public FullPage setArgs(InterXML interXML){
		this.xurl = interXML.getXurl();
		this.uid = interXML.getUid();
		this.risnews = interXML.getRisnews();
		this.xlist = interXML.getXlist();
		this.rlist = interXML.getRlist();
		this.xnext = interXML.getXnext();
		this.rnext = interXML.getRnext();
		this.xtitle = interXML.getXtitle();
		this.rtitle = interXML.getRtitle();
		this.xcontent = interXML.getXcontent();
		this.rcontent = interXML.getRcontent();
		this.xtime = interXML.getXtime();
		this.rtime = interXML.getRtime();
		this.xnewid = interXML.getXnewid();
		this.rnewid = interXML.getRnewid();
		return this;
	}
	@Override
	public void process(Page page) {
		Selectable url = page.getUrl();
		System.out.println("---->"+url);
		if(page.getHtml().xpath("base").match()){
			page.setUrl(page.getHtml().xpath("//base/@href"));
		}
		if(url.toString().matches(risnews)){
			page.putField("asrc", url.toString());
			page.putField("auid", uid);
			String tmp = page.getHtml().xpath(xtitle).regex(rtitle).toString();
			page.putField("atitle", tmp);
			List<String> srcs = page.getHtml().xpath(xcontent).xpath("//img/@src").all();
			tmp = page.getHtml().xpath(xcontent).regex(rcontent).toString();
			for(String src : srcs){
				tmp = tmp.replace(src, UrlUtils.canonicalizeUrl(src, page.getUrl().toString()));
			}
			page.putField("acontent", tmp);
			tmp = page.getHtml().xpath(xtime).regex(rtime).replace("[年月/]", "-").replace("[日]","").replace("(时|分|小时|分钟)", ":").replace("[秒种]", "").toString();
			page.putField("atime", DateUtil.Date2TimeStamp(tmp));
			tmp = url.regex(rnewid).toString();
			page.putField("akey", tmp);
		}else{
			String baseUrl = page.getUrl().toString();
			page.addTargetRequests(page.getHtml().xpath(xlist).all());
			String next = page.getHtml().xpath(xnext).regex(rnext).toString();
			next = UrlUtils.canonicalizeUrl(next, baseUrl);
			Request nextRequest = new Request(next);
			Map<String, Object> extras = new HashMap<String, Object>();
			System.out.println("extra -> "+page.getRequest());
			extras.put("_level",(Integer)page.getRequest().getExtra("_level")+1);
			nextRequest.setExtras(extras);
			page.addTargetRequest(nextRequest);
		}		
	}

	@Override
	public Site getSite() {
		return site;
	}

}
