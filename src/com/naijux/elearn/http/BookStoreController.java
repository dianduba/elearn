package com.naijux.elearn.http;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naijux.elearn.Book;
import com.naijux.elearn.BookStore;

@Controller
public class BookStoreController {
	
	@RequestMapping(value = "/store")
	public String listBooks(Model model) {
		List<Book> books = BookStore.getInstance().listBooks();
		model.addAttribute("books", books);
		
		return "bookStore";
	}
}
