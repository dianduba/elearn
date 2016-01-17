package cn.dianduba.elearn;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import cn.dianduba.elearn.dao.BookDao;
import cn.dianduba.elearn.dao.HotRegionDao;
import cn.dianduba.elearn.dao.PageDao;

@Lazy(false) 
@Component
public class JdbcBookStoreLoader implements BookStoreLoader {
	
	@Resource
	private BookDao bookDao;
	
	@Resource
	private PageDao pageDao;
	
	@Resource
	private HotRegionDao hotRegionDao;
	
	@Override
	public void load(BookStore bookStore) {
		
		List<Book> books = bookDao.listBooks();
		
		for (Book book : books) {
			bookStore.addBook(book);
			
			List<Page> pages = pageDao.getPagesOfBook(book.getId());
			
			for (Page page : pages) {
				book.addPage(page.getPageIndex(), page);
				
				page.setRegions(hotRegionDao.getHotRegionsOfPage(page.getId()));
				
			}
			
		}
		
		
	}
	
	@PostConstruct
	public void load() {
		this.load(BookStore.getInstance());
	}

}
