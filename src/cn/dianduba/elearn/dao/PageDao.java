package cn.dianduba.elearn.dao;

import java.util.List;

import cn.dianduba.elearn.Page;
import cn.dianduba.elearn.annotation.MyBaticsDao;

@MyBaticsDao
public interface PageDao {
	
	public void addPage(Page page);
	
	public int deletePage(int pageId);
	
	public int updatePage(Page page);
	
	public Page getPage(int pageId);
	
	public List<Page> getPagesOfBook(int bookId);
}
