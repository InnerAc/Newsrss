package newsrss.controller;

import java.util.List;

import newsrss.dao.Article;
import newsrss.dao.University;

public class RestController extends BaseController{
	public void univers(){
		List<University> univers = universityService.selectEnable();
		renderJson(univers);
	}
	public void news(){
		int uid = getParaToInt("uid");
		int page = getParaToInt("page");
		List<Article> articles = articleService.getArticlesByUid(uid, page);
		renderJson(articles);
	}
}
