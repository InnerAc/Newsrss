package newsrss.controller;

import com.jfinal.core.Controller;

import newsrss.dao.InterXML;
import newsrss.spider.FullPage;
import newsrss.spider.LevelLimitScheduler;
import us.codecraft.webmagic.Spider;

public class TestController extends Controller{
	public void index(){
		BaseController.spiderService.fullStart();
		renderText("success");
	}
}
