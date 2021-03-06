package newsrss.dao;

import java.util.List;

import org.omg.CORBA.INTERNAL;

import com.jfinal.plugin.activerecord.Model;

public class Article extends Model<Article>{
	private static final long serialVersionUID = 1L;
	public Article(){
		
	}
	public Article(String key,String title,String content,long time,String src, int uid){
		setAkey(key);
		setAtitle(title);
		setAcontent(content);
		setAtime(time);
		setAsrc(src);
		setAuid(uid);
	}
	public List<Article> selectByUniversity(int uid) {
		String sql = "SELECT * FROM article WHERE auid=? ORDER BY atime DESC";
		return find(sql, uid);
	}
	public List<Article> selectByUniversity(int uid,int index,int length) {
		String sql = "SELECT aid,atitle,atime FROM article WHERE auid=? ORDER BY atime DESC LIMIT ?,?";
		return find(sql, uid,index,length);
	}
	public int insert(Article article){
		article.save();
		return article.getInt("aid");
	}
	
	
	public String getAkey() {
		return getStr("akey");
	}
	public void setAkey(String akey) {
		set("akey",akey);
	}
	public String getAtitle() {
		return getStr("atitle");
	}
	public void setAtitle(String atitle) {
		set("atitle",atitle);
	}
	public String getAcontent() {
		return getStr("acontent");
	}
	public void setAcontent(String acontent) {
		set("acontent",acontent);
	}
	public long getAtime() {
		return getLong("atime");
	}
	public void setAtime(long atime) {
		set("atime",atime);
	}
	public int getAuid() {
		return getInt("auid");
	}
	public void setAuid(int auid) {
		set("auid",auid);
	}
	public String getAsrc() {
		return getStr("asrc");
	}
	public void setAsrc(String asrc) {
		set("asrc",asrc);
	}
}
