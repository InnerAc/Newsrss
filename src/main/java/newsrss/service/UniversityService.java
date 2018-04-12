package newsrss.service;

import java.util.List;

import newsrss.dao.University;

public class UniversityService extends BaseService{

	public List<University> selectEnable(){
		return universityDAO.selectEnable();
	}
	public List<University> selectUnenable(){
		return universityDAO.selectUnenable();
	}
	public List<University> selectAll(){
		return universityDAO.selectAll();
	}
	
	public int addUniversity(String uname){
		int uid = -1;
		try {
			uid = universityDAO.insert(uname);
		} catch (Exception e) {
		}
		return uid;
	}
	public boolean changeExtend(int uid,String spiderName,int extend){
		return universityDAO.changeExtend(uid, spiderName, extend);
	}
	public boolean update(int uid,String uname){
		University university = universityDAO.findById(uid);
		university.setUname(uname);
		return university.update();
	}
	
	public University selectById(int uid){
		return universityDAO.findById(uid);
	}
	
	public boolean deleteById(int uid){
		universityDAO.deleteById(uid);
		return interXMLDAO.deleteByUid(uid);
	}
	public void updateLvl(String sorts) {
		universityDAO.setUnable();
		String[] uids = sorts.split(",");
		int n = uids.length;
		for(int i=0;i<n;i++){
			universityDAO.update(n-i,Integer.valueOf(uids[i]));
		}
	}
	public int getDefaultUid() {
		return universityDAO.selectDefultUid();
	}
}
