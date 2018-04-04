package newsrss.controller;

import com.jfinal.core.Controller;

import newsrss.service.ArticleService;
import newsrss.service.InterXMLService;
import newsrss.service.SpiderService;
import newsrss.service.UniversityService;

public class BaseController extends Controller{
	public static SpiderService spiderService = new SpiderService();
	public static UniversityService universityService = new UniversityService();
	public static InterXMLService interXMLService = new InterXMLService();
	public static ArticleService articleService = new ArticleService();
}
