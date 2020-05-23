package com.bookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bookstore.bean.Book;

@Service
public interface BookService {
	
	public boolean addBook(Book book);
	
	public List<Book> searchBook(Integer isbn, String title, String author);
	
	public Book buyBook(Integer isbn);
	
	public List<String> searchMediaCover(String title);
}
