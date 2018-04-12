package newsrss.controller;

import newsrss.dao.Article;

public class NewsController extends BaseController{
	public void index(){
		String suid = getPara();
		int uid = 0;
		if(suid == null){
			uid = universityService.getDefaultUid();
		}else {
			uid = Integer.valueOf(suid);
		}
		setAttr("uid", uid);
		render("index.jsp");
	}
	
	public void news(){
		int aid = getParaToInt();
		Article article = articleService.articleDAO.findById(aid);
		setAttr("article", article);
		render("news.jsp");
	}
}
