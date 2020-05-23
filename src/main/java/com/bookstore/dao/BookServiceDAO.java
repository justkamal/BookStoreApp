package com.bookstore.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bookstore.bean.Book;
import com.bookstore.bean.Post;

@Repository
public interface BookServiceDAO {
	
	public static final String INSERT = "INSERT INTO book VALUES";
	
	public boolean addBook(Book book);
	
	public List<Book> searchBook(Integer isbn, String title, String author);
	
	public Book buyBook(Integer isbn);
	
	public List<Post> getMediaCoverPosts();
}
