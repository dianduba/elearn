package cn.dianduba.elearn.dao;

import cn.dianduba.elearn.Resource;
import cn.dianduba.elearn.annotation.MyBaticsDao;

@MyBaticsDao
public interface ResourceDao {
	
	public void addResource(Resource resource);
	
	public int deleteResource(int id);
	
	public int updateResource(Resource resource);
	
	public Resource getResrouce(int id);
	
	
}
