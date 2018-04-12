package newsrss.common;

public class RSSConstants {
	public static int NEWS_DEEP = 1;
	public static int NEWS_LIMIT = 10;
	public static String EXTEND_SPIDER_PATH = "newsrss.spider.extend.";
	public static String getExtendSpider(String spiderName){
		return EXTEND_SPIDER_PATH+spiderName;
	}
}
