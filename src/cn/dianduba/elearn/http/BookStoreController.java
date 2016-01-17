package cn.dianduba.elearn.http;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dianduba.elearn.BookStore;

import cn.dianduba.elearn.Book;

@Controller
public class BookStoreController {
	
	@RequestMapping(value = "/store")
	public String listBooks(Model model) {
		List<Book> books = BookStore.getInstance().listBooks();
		model.addAttribute("books", books);
		
		return "bookStore";
	}
}
