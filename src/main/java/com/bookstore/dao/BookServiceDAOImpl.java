package com.bookstore.dao;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.bookstore.bean.Book;
import com.bookstore.bean.Post;

@Repository
public class BookServiceDAOImpl implements BookServiceDAO {

	@Autowired
	private EntityManager entityManager;

	@Override
	public boolean addBook(Book book) {
		Session current = entityManager.unwrap(Session.class);
		current.save(book);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> searchBook(Integer isbn, String title, String author) {
		Session current = entityManager.unwrap(Session.class);
		Query result = null;

		StringBuilder query = new StringBuilder("FROM Book WHERE ");

		if (isbn != null) {
			query.append("isbn = " + isbn);
		}
		if (title != null) {
			if(isbn != null)
				query.append(" AND ");
			query.append(" title LIKE '%" + title + "%'");
		}
		if (author != null) {
			if(title != null)
				query.append(" AND ");
			query.append(" author LIKE '%" + author + "%'");
		}

		result = current.createQuery(query.toString());

		List<Book> bookList = result.list();
		System.out.println(bookList);
		return bookList;
	}

	@Override
	public Book buyBook(Integer isbn) {
		Session current = entityManager.unwrap(Session.class);
		Book book = current.get(Book.class, isbn);
		if(book.getAvailable_copies() > 1)
			book.setAvailable_copies(book.getAvailable_copies() - 1);
		current.update(book);
		return book;
	}

	@Override
	public List<Post> getMediaCoverPosts() {
		RestTemplate externalAPI = new RestTemplate();
		Post[] posts = externalAPI.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
		
		return Arrays.asList(posts);
	}
}