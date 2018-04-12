package newsrss.spider;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class TemplatePage implements PageProcessor{

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	protected  boolean isFull = false;
	protected int uid = -1;
	
	public void isFull(boolean isfull){
		this.isFull = isfull;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	/**
	 * 根据输入的数字返回相应深度的页面
	 * @param number
	 * @return
	 */
	public abstract List<String> getStartUrl(int number);

}
