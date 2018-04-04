package newsrss.controller;

import java.util.List;
import java.util.Map;

import newsrss.dao.InterXML;
import newsrss.dao.University;

public class AdminController extends BaseController{
	public void index(){
		List<University> universities = universityService.selectAll();
		setAttr("universities", universities);
		render("index.jsp");
	}
	
	/**
	 * 添加学校
	 */
	public void addUni(){
		String uname = getPara("uname");
		int uid = universityService.addUniversity(uname);
		renderText(uid+"");
	}
	
	/**
	 * 修改学校名称
	 */
	public void editUname(){
		String uname = getPara("uname");
		int uid = getParaToInt("uid");
		boolean isok = universityService.update(uid, uname);
		if (isok) {
			renderText("0");
		}else{
			renderText("1");
		}
	}
	
	/**
	 * 学校详情页面
	 */
	public void univer(){
		int uid = getParaToInt();
		University university = universityService.selectById(uid);
		List<InterXML> inters = interXMLService.selectByUid(uid);
		setAttr("univer", university);
		setAttr("inters", inters);
		render("univer.jsp");
	}
	
	/**
	 * 解析规则详情页面
	 */
	public void xml(){
		int xid = getParaToInt();
		InterXML inter = interXMLService.interXMLDAO.findById(xid);
		setAttr("inter", inter);
		render("xml.jsp");
	}
	
	public void updateXML(){
		InterXML inter = getModel(InterXML.class,"");
		int xid = inter.getXid();
		boolean isok = interXMLService.updateInterXML(inter);
		if(isok){
			redirect("/admin/xml/"+xid);
		}else {
			render("error.jsp");
		}
	}
	
	public void addXML(){
		if (getRequest().getMethod().equals("POST")) {
			InterXML inter = getModel(InterXML.class,"");
			System.out.println(inter.getUid());
			int xid = interXMLService.addInterXML(inter);
			redirect("/admin/xml/"+xid);
		} else {
			int uid = getParaToInt();
			setAttr("uid", uid);
			render("xml_add.jsp");
		}
	}
	
	public void deleteXML(){
		int xid = getParaToInt();
		int uid = interXMLService.deleteInterXML(xid);
		redirect("/admin/univer/"+uid);
	}
	
	public void sort(){
		List<University> ableUniver = universityService.selectEnable();
		List<University> unableUniver = universityService.selectUnenable();
		
		setAttr("ableUniver", ableUniver);
		setAttr("unableUniver", unableUniver);
		
		render("sort.jsp");
		
	}
	
	/**
	 * 对高校顺序排序，并启动一次完整爬取
	 */
	public void sortlist(){
		String sorts = getPara("sorts");
		universityService.updateLvl(sorts);
		spiderService.clearSpider();
		spiderService.init();
		renderText("0");
	}
	
	public void spider(){
		render("spider.jsp");
	}
	
	public void spiderInfos(){
		List<Map> res = spiderService.monitor();
		renderJson(res);
	}
	
	public void startSpider(){
		spiderService.cronStart();
		renderText("0");
	}
}
