package cn.dianduba.elearn.dao;

import java.util.List;

import cn.dianduba.elearn.Book;
import cn.dianduba.elearn.annotation.MyBaticsDao;

@MyBaticsDao
public interface BookDao {
	
	public void addBook(Book book);
	
	public int deleteBook(int bookId);
	
	public int updateBook(Book book);
	
	public Book getBook(int bookId);
	
	public List<Book> listBooks();
}
