package newsrss.dao;

import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;

public class InterXML extends Model<InterXML>{
	private static final long serialVersionUID = 1L;

	public List<InterXML> selectByUniversity(int uid){
		String sql = "select * from interxml where uid=?";
		return find(sql, uid);
	}
	public boolean deleteByUid(int uid){
		String sql = "DELETE FROM interxml where uid=?";
		Db.update(sql,uid);
		return true;
	}
	
	public int getXid(){
		return getInt("xid");
	}
	public String getXurl() {
		return getStr("xurl");
	}
	public void setXurl(String xurl) {
		set("xurl",xurl);
	}
	public int getUid() {
		return getInt("uid");
	}
	public void setUid(int uid) {
		set("uid",uid);
	}
	public String getRisnews() {
		return getStr("risnews");
	}
	public void setRisnews(String risnews) {
		set("risnews",risnews);
	}
	public String getXlist() {
		return getStr("xlist");
	}
	public void setXlist(String xlist) {
		set("xlist",xlist);
	}
	public String getRlist() {
		return getStr("rlist");
	}
	public void setRlist(String rlist) {
		set("rlist",rlist);
	}
	public String getXnext() {
		return getStr("xnext");
	}
	public void setXnext(String xnext) {
		set("xnext",xnext);
	}
	public String getRnext() {
		return getStr("rnext");
	}
	public void setRnext(String rnext) {
		set("rnext",rnext);
	}
	public String getXtitle() {
		return getStr("xtitle");
	}
	public void setXtitle(String xtitle) {
		set("xtitle",xtitle);
	}
	public String getRtitle() {
		return getStr("rtitle");
	}
	public void setRtitle(String rtitle) {
		set("rtitle",rtitle);
	}
	public String getXcontent() {
		return getStr("xcontent");
	}
	public void setXcontent(String xcontent) {
		set("xcontent",xcontent);
	}
	public String getRcontent() {
		return getStr("rcontent");
	}
	public void setRcontent(String rcontent) {
		set("rcontent",rcontent);
	}
	public String getXtime() {
		return getStr("xtime");
	}
	public void setXtime(String xtime) {
		set("xtime",xtime);
	}
	public String getRtime() {
		return getStr("rtime");
	}
	public void setRtime(String rtime) {
		set("rtime",rtime);
	}
	public String getXnewid() {
		return getStr("xnewid");
	}
	public void setXnewid(String xnewid) {
		set("xnewid",xnewid);
	}
	public String getRnewid() {
		return getStr("rnewid");
	}
	public void setRnewid(String rnewid) {
		set("rnewid",rnewid);
	}
	
}
