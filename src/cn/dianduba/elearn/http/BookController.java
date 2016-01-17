package cn.dianduba.elearn.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dianduba.elearn.BookStore;
import cn.dianduba.elearn.Page;
import cn.dianduba.elearn.dao.HotRegionDao;
import cn.dianduba.elearn.system.UserUtils;
import cn.dianduba.elearn.Book;
import cn.dianduba.elearn.system.dao.UserDao;

@Controller
public class BookController {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private HotRegionDao hotRegionDao;
	
	@RequestMapping(value = "/book")
	public String getBook(int bookId, Model model) {
		
		Book book = BookStore.getInstance().getBook(bookId);
		
		model.addAttribute("book", book);
		
		if (UserUtils.isUser()) {
			List<Integer> bookIds = userDao.getBooksOfUser(UserUtils.getUserId());
			List<Book> books = new ArrayList<Book>();
			for (int id : bookIds) {
				Book bk = BookStore.getInstance().getBook(id);
				books.add(bk);
			}
			
			model.addAttribute("books", books);
		}
		
		
		return "book";
		
	}
	
	@RequestMapping(value = "/record")
	@ResponseBody
	public Object recordClick(int bookId, int pageIndex, int regionId) {
		
		if (UserUtils.isUser()) {
			userDao.recordClick(UserUtils.getUserId(), bookId, pageIndex, regionId);
			userDao.useHits(UserUtils.getUsername(), 1);
		}
		
		return null;
	}
	
	@RequestMapping(value = "/bookPage")
	@ResponseBody
	public Object getPage(int bookId, int pageIndex) {
		Map<String, Object> result = new HashMap<String, Object>();
		Book book = BookStore.getInstance().getBook(bookId);
		if (book == null) {
			result.put("code", 1);
			return result;
		}
		
		Page page = pageIndex == -1 ? book.getFirstPage() : book.getPage(pageIndex);
		if (page == null) {
			result.put("code", 1);
			return result;
		}
		
		if (!UserUtils.isUser() && pageIndex > 10) {
			result.put("code", 1);
			result.put("message", "需要登录后才能继续点读哦！请先登录！");
			return result;
		}
		
		result.put("pageInfo", page);
		result.put("code", 0);
		
		return result;
		
	}
	
//	@RequestMapping(value = "/pageImage")
//	public String getPageImage(String bookId, int pageIndex) {
//		Book book = BookStore.getInstance().getBook(bookId);
//		Page page = book.getPage(pageIndex);
//		
//		if (!UserUtils.isUser() && pageIndex > 10)
//			return "";
//			
//		return "forward:" + page.getResource().getUrl();
//		
//	}
//	
//	@RequestMapping(value = "/regionMedia")
//	public String getRegionMedia(String bookId, int pageIndex, int regionId) {
//		Book book = BookStore.getInstance().getBook(bookId);
//		Page page = book.getPage(pageIndex);
//		HotRegion region = page.getHotRegion(regionId);
//		
//		if (UserUtils.isUser()) {
//			int count = userDao.useHits(UserUtils.getUsername(), 1);
//			if (count == 0)
//				return "";
//		}
//		
//		if (!UserUtils.isUser() && pageIndex > 10)
//			return "";
//		
//		return "forward:" + region.getResource().getUrl();
//		
//	}
	
	
		
}
