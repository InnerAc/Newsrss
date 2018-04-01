package newsrss.controller;

import com.jfinal.core.Controller;

import newsrss.service.SpiderService;

public class BaseController extends Controller{
	public static SpiderService spiderService = new SpiderService();
}
