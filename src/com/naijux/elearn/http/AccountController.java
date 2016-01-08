package com.naijux.elearn.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naijux.elearn.Book;
import com.naijux.elearn.BookStore;
import com.naijux.elearn.system.User;
import com.naijux.elearn.system.UserUtils;
import com.naijux.elearn.system.dao.UserDao;

@Controller
public class AccountController {
	
	@Resource
	UserDao userDao;
	
	@RequestMapping(value = "/account")
	public String accountPage(Model model) {
		String username = UserUtils.getUsername();
		User user = userDao.getUserByUsername(username);
		
		model.addAttribute("user", user);
		
		return "account";
	}
	
	@RequestMapping(value = "/favorite")
	public String favoritePage(Model model) {
		
		List<String> bookIds = userDao.getBooksOfUser(UserUtils.getUserId());
		List<Book> books = new ArrayList<Book>();
		for (String bookId : bookIds) {
			Book book = BookStore.getInstance().getBook(bookId);
			books.add(book);
		}
		
		model.addAttribute("books", books);
		
		return "favorite";
	}
	
	@RequestMapping(value = "/register")
	public String register(User user, Model model) {
		user.setBalanceHits(1000);
		
		try {
			userDao.addUser(user);
			
			Subject subject = SecurityUtils.getSubject();  
			UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());  
//			token.setRememberMe(true);  
			subject.login(token);   
		
			return "redirect:/account.do";
		}
		catch(Exception ex) {
			ex.printStackTrace();
			model.addAttribute("user", user);
			return "register";
		}
	}
	
	@RequestMapping(value="/favoriteBook")
	@ResponseBody
	public Object favoriteBook(String bookId) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			userDao.favoriteBook(UserUtils.getUserId(), bookId);
			result.put("code", 0);
			result.put("message", "收藏成功！");
		}
		catch(Exception ex) {
			result.put("code", 1);
			result.put("message", "此书已经收藏！");
		}
		
		return result;
	}
	
	@RequestMapping(value = "/cancelFavorite")
	@ResponseBody
	public Object cancelFavorite(String bookId) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		try {
			int count = userDao.cancelFavorite(UserUtils.getUserId(), bookId);
			result.put("code", count > 0 ? 0 : 1);
			result.put("message", count > 0 ? "取消收藏成功！" : "此书还没收藏！无法取消！");
		}
		catch(Exception ex) {
			result.put("code", 1);
			result.put("message", "取消收藏失败！" + ex.getMessage());
		}
		
		return result;
		
	}
	
}
