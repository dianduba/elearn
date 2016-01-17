package cn.dianduba.elearn;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dianduba.elearn.dao.BookDao;
import cn.dianduba.elearn.dao.HotRegionDao;
import cn.dianduba.elearn.dao.PageDao;
import cn.dianduba.elearn.dao.ResourceDao;

public class Magirate {
	
	private ResourceDao resourceDao;
	
	private BookDao bookDao;
	
	private PageDao pageDao;
	
	private HotRegionDao hotRegionDao;
	
	
	
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public HotRegionDao getHotRegionDao() {
		return hotRegionDao;
	}

	public void setHotRegionDao(HotRegionDao hotRegionDao) {
		this.hotRegionDao = hotRegionDao;
	}

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext ctx = null;
		try {
			ctx = new ClassPathXmlApplicationContext("/spring-context.xml"); 
			
			BookStoreLoader loader = new FileBookStoreLoader("E:/workspace/elearn/web/books", "/");
			loader.load(BookStore.getInstance());
			
			ResourceDao resourceDao = ctx.getBean(ResourceDao.class);
			BookDao bookDao = ctx.getBean(BookDao.class);
			
			PageDao pageDao = ctx.getBean(PageDao.class);
			
			HotRegionDao hotRegionDao = ctx.getBean(HotRegionDao.class);
			
			Magirate magirate = new Magirate();
			
			magirate.setBookDao(bookDao);
			magirate.setHotRegionDao(hotRegionDao);
			magirate.setPageDao(pageDao);
			magirate.setResourceDao(resourceDao);
			
			magirate.magirate();
			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			if (ctx != null)
				ctx.close();
		}
		
	}
	
	public void magirate() {
		List<Book> books = BookStore.getInstance().listBooks();
		
		for (Book book : books) {
			Resource resource = book.getResource();
			
			resourceDao.addResource(resource);
			
			book.setFaceImageId(resource.getId());
			
			bookDao.addBook(book);
			
			Iterator<Page> pages = book.listPages();
			
			while(pages.hasNext()) {
				Page page = pages.next();
				
				Resource pageResource = page.getResource();
				resourceDao.addResource(pageResource);
				
				page.setImageId(pageResource.getId());
				page.setBookId(book.getId());
				
				pageDao.addPage(page);
				
				List<HotRegion> regions = page.getRegions();
				
				for (HotRegion region : regions) {
					Resource regionResource = region.getResource();
					
					resourceDao.addResource(regionResource);
					
					region.setMediaId(regionResource.getId());
					region.setPageId(page.getId());
					
					hotRegionDao.addHotRegion(region);
					
				}
				
				
			}
			
		}
		
		
	}
}
