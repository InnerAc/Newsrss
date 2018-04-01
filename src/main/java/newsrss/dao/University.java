package newsrss.dao;

import java.util.List;

import com.jfinal.plugin.activerecord.Model;

public class University extends Model<University>{
	private static final long serialVersionUID = 1L;
	
	public int insert(String uname){
		University university = new University();
		university.setUname(uname);
		university.setLvl(0);
		university.save();
		return university.getInt("uid");
	}
	
	public List<University> selectEnable(){
		String sql = "select * from university where ulvl > 0";
		return find(sql);
	}
	public List<University> selectAll(){
		String sql = "select * from university order by ulvl";
		return find(sql);
	}
	
	public String getUname() {
		return getStr("uname");
	}
	public void setUname(String uname) {
		set("uname",uname);
	}
	public int getLvl(){
		return getInt("lvl");
	}
	public void setLvl(int lvl){
		set("lvl", lvl);
	}
}
