package newsrss.spider;

import java.util.List;

import newsrss.dao.Article;
import newsrss.dao.InterXML;
import newsrss.utils.DateUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.UrlUtils;

public class RealPage implements PageProcessor{
	private Site site = Site.me().setRetryTimes(3).setSleepTime(200);
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
	
	public RealPage setArgs(InterXML interXML){
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
		if(page.getUrl().toString().matches(risnews)){
			Article article = new Article();
			article.setAsrc(page.getUrl().toString());
			article.setAuid(uid);
			String tmp = page.getHtml().xpath(xtitle).regex(rtitle).toString();
			article.setAtitle(tmp);
			List<String> srcs = page.getHtml().xpath(xcontent).xpath("//img/@src").all();
			tmp = page.getHtml().xpath(xcontent).regex(rcontent).toString();
			for(String src : srcs){
				tmp = tmp.replace(src, UrlUtils.canonicalizeUrl(src, page.getUrl().toString()));
			}
			article.setAcontent(tmp);
			tmp = page.getHtml().xpath(xtime).regex(rtime).replace("[年月/]", "-").replace("[日]","").toString();
			article.setAtime(DateUtil.Date2TimeStamp(tmp));
			tmp = page.getUrl().regex(rnewid).toString();
			article.setAkey(tmp);
			article.save();
		}else{
			String rootUrl = page.getUrl().regex("(http.*://[a-zA-z\\d\\.]+)").toString();
			page.addTargetRequests(page.getHtml().xpath(xlist).all());
		}		
	}

	@Override
	public Site getSite() {
		return site;
	}
}
