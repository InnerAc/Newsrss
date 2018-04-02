package newsrss.service;

import java.util.LinkedList;
import java.util.List;

import newsrss.dao.InterXML;
import newsrss.dao.University;
import newsrss.spider.NewsSpider;
import newsrss.spider.RealPage;
import us.codecraft.webmagic.Spider.Status;
import newsrss.spider.FullPage;

public class SpiderService extends BaseService{

	private final LinkedList<NewsSpider> realQueue = new LinkedList<>();
	private final LinkedList<NewsSpider> fullQueue = new LinkedList<>();
	
	/**
	 * 启动时完整爬取模式
	 */
	public void init(){
		List<University> universities = universityDAO.selectEnable();
		for(University university : universities){
			List<InterXML> inters = interXMLDAO.selectByUniversity(university.getUid());
			for(InterXML inter : inters){
				NewsSpider spider = new NewsSpider(new FullPage().setArgs(inter), inter, university);
				fullQueue.add(spider);
			}
		}
		fullStart();
	}
	
	/**
	 * 完整爬取启动
	 */
	public void fullStart(){
		for(NewsSpider spider : fullQueue){
			if(spider.getStatus() != Status.Running){
				spider.start();
			}
		}
	}
	
	public void cronStart(){
		for(NewsSpider spider : fullQueue){
			if(spider.getStatus() != Status.Running){
				int xid = spider.getXid();
				fullQueue.remove(spider);
				InterXML inter = interXMLDAO.findById(xid);
				University university = universityDAO.findById(inter.getUid());
				spider = new NewsSpider(new RealPage().setArgs(inter), inter, university);
				realQueue.add(spider);
			}
		}
		System.out.println(fullQueue.size()+" "+realQueue.size());
		for(NewsSpider spider : realQueue){
			if(spider.getStatus() != Status.Running){
				spider.start();
			}
		}
	}
}
