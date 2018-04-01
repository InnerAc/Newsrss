package newsrss.controller;

import com.jfinal.core.Controller;

public class TestController extends Controller{
	public void index(){
		renderText("success");
	}
}
