package newsrss.service;

import java.util.List;

import newsrss.dao.InterXML;

public class InterXMLService extends BaseService{

	public int addInterXML(InterXML inter){
		try {
			inter.save();
		} catch (Exception e) {
			System.out.println(e);
		}
		return inter.getXid();
	}
	
	public List<InterXML> selectByUid(int uid){
		return interXMLDAO.selectByUniversity(uid);
	}
	
	public boolean updateInterXML(InterXML inter){
		return inter.update();
	}
	
	public int deleteInterXML(int xid){
		int uid = interXMLDAO.findById(xid).getUid();
		interXMLDAO.deleteById(xid);
		return uid;
	}
	
	public boolean deleteByUid(int uid){
		return interXMLDAO.deleteByUid(uid);
	}
}
