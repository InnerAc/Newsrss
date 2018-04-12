package newsrss.service;

import java.util.List;

import newsrss.common.RSSConstants;
import newsrss.dao.Article;

public class ArticleService extends BaseService{
	public List<Article> getArticlesByUid(int uid,int start){
		return articleDAO.selectByUniversity(uid, start, RSSConstants.NEWS_LIMIT);
	}
}
