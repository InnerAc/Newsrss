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
		Map<String, Object> extras = new HashMap<String, Object>();
		extras.put("_level",0);
		Request request = new Request(interXML.getXurl());
		request.setExtras(extras);
		addRequest(request);
		setScheduler(new LevelLimitScheduler(5));
		thread(3);
	}

}
