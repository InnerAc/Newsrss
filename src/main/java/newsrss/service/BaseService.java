package newsrss.service;

import newsrss.dao.Article;
import newsrss.dao.InterXML;
import newsrss.dao.University;

public class BaseService {
	public static final Article articleDAO = new Article();
	public static final University universityDAO = new University();
	public static final InterXML interXMLDAO = new InterXML();
}
