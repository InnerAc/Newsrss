package newsrss.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import newsrss.common.RSSConstants;
import newsrss.dao.InterXML;
import newsrss.dao.University;
import newsrss.spider.NewsSpider;
import newsrss.spider.RealPage;
import newsrss.spider.TemplatePage;
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
			if(university.getExtend() == 0){
				List<InterXML> inters = interXMLDAO.selectByUniversity(university.getUid());
				for(InterXML inter : inters){
					NewsSpider spider = new NewsSpider(new FullPage().setArgs(inter), inter, university);
					fullQueue.add(spider);
				}
			}else {
				String[] spiderNames = university.getSpider().split(",");
				for(String spiderName : spiderNames){
					Class spiderClass;
					try {
						spiderClass = Class.forName(RSSConstants.getExtendSpider(spiderName));
						TemplatePage spiderPage = (TemplatePage) spiderClass.newInstance();
						NewsSpider spider = new NewsSpider(spiderPage, university, true,spiderName);
						fullQueue.add(spider);
					} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
						System.out.println(e);
					}
				}
			}
		}
		fullStart();
//		cronStart();
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
	
	/**
	 * 定时爬取任务
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void cronStart(){
		for(NewsSpider spider : fullQueue){
			if(spider.getStatus() != Status.Stopped){
				spider.stop();
			}
			if(spider.isExtend){
				int uid = spider.getXid();
				String spiderName = spider.getSpiderName();
				University university = universityDAO.findById(uid);
				try {
					Class spiderClass = Class.forName(RSSConstants.getExtendSpider(spiderName));
					TemplatePage spiderPage = (TemplatePage) spiderClass.newInstance();
					spider = new NewsSpider(spiderPage, university, false,spiderName);
					realQueue.add(spider);
				} catch (Exception e) {
					System.out.println("类加载有问题："+e);
				}
			}else{
				int xid = spider.getXid();
				InterXML inter = interXMLDAO.findById(xid);
				University university = universityDAO.findById(inter.getUid());
				spider = new NewsSpider(new RealPage().setArgs(inter), inter, university);
				realQueue.add(spider);
			}
		}
		fullQueue.clear();
		for(NewsSpider spider : realQueue){
			if(spider.getStatus() != Status.Running){
				spider.start();
			}
		}
	}

	public List<Map> monitor(){
		List<Map> res = new ArrayList<>();
		for(NewsSpider spider : fullQueue){
			res.add(spider.getInfo());
		}
		for(NewsSpider spider : realQueue){
			res.add(spider.getInfo());
		}
		return res;
	}
	
	/**
	 * 清除所有爬虫进程
	 */
	public void clearSpider() {
		System.out.println("clear start");
		for(NewsSpider spider : fullQueue){
			if(spider.getStatus() != Status.Stopped){
				spider.stop();
			}
		}
		fullQueue.clear();
		for(NewsSpider spider : realQueue){
			if(spider.getStatus() != Status.Stopped){
				spider.stop();
			}
		}
		realQueue.clear();
		System.out.println("clear ok");
	}
}
