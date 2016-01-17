package cn.dianduba.elearn.dao;

import java.util.List;

import cn.dianduba.elearn.HotRegion;
import cn.dianduba.elearn.annotation.MyBaticsDao;

@MyBaticsDao
public interface HotRegionDao {
	
	public void addHotRegion(HotRegion region);
	
	public int deleteHotRegion(int regionId);
	
	public int updateHotRegion(HotRegion region);
	
	public HotRegion getHotRegion(int regionId);
	
	public List<HotRegion> getHotRegionsOfPage(int pageId);
}
