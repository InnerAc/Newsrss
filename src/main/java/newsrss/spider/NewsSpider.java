package newsrss.spider;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import newsrss.common.RSSConstants;
import newsrss.dao.InterXML;
import newsrss.dao.University;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class NewsSpider extends Spider{

	private int xid = 0;
	private String uname = null;
	private String url = null;
	private String sTime = null;
	private String spiderName = null;
	public boolean isExtend = false;
	public int getXid(){
		return this.xid;
	}
	public String getUname(){
		return this.uname;
	}
	public String getSpiderName() {
		return spiderName;
	}
	public NewsSpider(PageProcessor pageProcessor) {
		super(pageProcessor);
	}
	
	@SuppressWarnings("deprecation")
	public NewsSpider(PageProcessor pageProcessor, InterXML interXML,University university){
		super(pageProcessor);
		xid = interXML.getXid();
		uname = university.getUname();
		url = interXML.getXurl();
		sTime = new Date().toLocaleString();
		Map<String, Object> extras = new HashMap<String, Object>();
		extras.put("_level",0);
		Request request = new Request(interXML.getXurl());
		request.setExtras(extras);
		addRequest(request);
		setScheduler(new LevelLimitScheduler(RSSConstants.NEWS_DEEP));
		addPipeline(new MysqlPipeline());
		thread(3);
	}
	
	/**
	 * 添加外部Spider
	 * @param pageProcessor
	 * @param university
	 * @param isFull
	 */
	public NewsSpider(TemplatePage pageProcessor, University university,boolean isFull,String spiderName){
		super(pageProcessor);
		this.isExtend = true;
		this.spiderName = spiderName;
		xid = university.getUid();
		uname = university.getUname();
		url = pageProcessor.getStartUrl(1).get(0);
		sTime = new Date().toLocaleString();
		pageProcessor.setUid(xid);
		if(isFull){
			pageProcessor.isFull(true);
			List<String> urls = pageProcessor.getStartUrl(RSSConstants.NEWS_DEEP);
			for(int i=0;i<urls.size();i++){
				Map<String, Object> extras = new HashMap<String, Object>();
				extras.put("_level",i);
				Request request = new Request(urls.get(i));
				request.setExtras(extras);
				addRequest(request);
			}
		}else{
			pageProcessor.isFull(false);
			Map<String, Object> extras = new HashMap<String, Object>();
			extras.put("_level",0);
			Request request = new Request(url);
			request.setExtras(extras);
			addRequest(request);
		}
		setScheduler(new LevelLimitScheduler(RSSConstants.NEWS_DEEP));
		addPipeline(new MysqlPipeline());
		thread(3);
	}
	
	public Map<String, Object> getInfo(){
		Map<String, Object> info = new HashMap<>();
		info.put("time", this.sTime);
		info.put("status", getStatus());
		info.put("uname", this.uname);
		info.put("xid", this.xid);
		info.put("count", getPageCount());
		return info;
	}
	
	@Override
	public void start(){
		Map<String, Object> extras = new HashMap<String, Object>();
		extras.put("_level",0);
		Request request = new Request(url);
		request.setExtras(extras);
		addRequest(request);
		super.start();
	}

}
