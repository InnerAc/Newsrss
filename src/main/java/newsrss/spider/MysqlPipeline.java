package newsrss.spider;

import java.util.Map;

import newsrss.dao.Article;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MysqlPipeline implements Pipeline{

	@Override
	public void process(ResultItems resultItems, Task task) {
		Article article = new Article();
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			article.set(entry.getKey(), entry.getValue());
		}
		try {
			article.save();
		} catch (Exception e) {
//			System.out.println(article.getAtitle()+"-->"+e);
		}
		
	}

}
