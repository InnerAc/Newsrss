package newsrss.spider;

import java.util.HashMap;
import java.util.Map;

import newsrss.dao.InterXML;
import newsrss.dao.University;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class NewsSpider extends Spider{

	private int xid = 0;
	private String uname = null;
	private String url = null;
	
	public int getXid(){
		return this.xid;
	}
	public String getUname(){
		return this.uname;
	}
	
	public NewsSpider(PageProcessor pageProcessor) {
		super(pageProcessor);
	}
	
	public NewsSpider(PageProcessor pageProcessor, InterXML interXML,University university){
		super(pageProcessor);
		xid = interXML.getXid();
		uname = university.getUname();
		url = interXML.getXurl();
		Map<String, Object> extras = new HashMap<String, Object>();
		extras.put("_level",0);
		Request request = new Request(interXML.getXurl());
		request.setExtras(extras);
		addRequest(request);
		setScheduler(new LevelLimitScheduler(3));
		thread(3);
	}
	
	public Map<String, Object> getInfo(){
		Map<String, Object> info = new HashMap<>();
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
