package com.naijux.elearn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStore {
	
	private static BookStore instance;
	
	private Map<String, Book> books = new HashMap<String, Book>();
	
	public static BookStore getInstance() {
		if (instance == null)
			instance = new BookStore();
		
		return instance;
	}
	
	public void addBook(Book book) {
		books.put(book.getId(), book);
	}
	
	public Book getBook(String bookId) {
		return books.get(bookId);
	}
	
	public void refreshBook(Book book) {
		books.remove(book.getId());
		books.put(book.getId(), book);
	}
	
	public Book remove(String bookId) {
		return books.remove(bookId);
	}
	
	public List<Book> listBooks() {
		return Arrays.asList(books.values().toArray(new Book[]{}));
	}
	
	public void clear() {
		books.clear();
	}
}
