package bookstore.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.bookstore.bean.Book;
import com.bookstore.bean.Post;
import com.bookstore.dao.BookServiceDAO;
import com.bookstore.exception.FieldValueTooSmallException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.service.BookService;
import com.bookstore.ui.APIClient;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {APIClient.class})
public class APIUnitTest {
	
	@Autowired
	private BookService bookService; 
	
	@MockBean
	private BookServiceDAO bookServiceDAO;
	
	@Test
	public void addBookTest() {
		Book book = new Book();
		book.setIsbn(123);
		book.setTitle("Title");
		book.setAuthor("Author");
		book.setPrice(90.7f);
		book.setAvailable_copies(1);
		
		when(bookServiceDAO.addBook(book)).thenReturn(true);
		assertTrue(bookService.addBook(book));
	}
	
	@Test
	public void buyBookTest() {
		int isbn = 123;
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle("Title");
		book.setAuthor("Author");
		book.setPrice(90.7f);
		book.setAvailable_copies(1);
		
		when(bookServiceDAO.buyBook(isbn)).thenReturn(book);
		assertEquals(book, bookService.buyBook(isbn));
	}
	
	@Test
	public void searchBookTest() {
		int isbn = 123;
		String title = "title";
		String author = "author";
		
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(90.7f);
		book.setAvailable_copies(1);
		
		List<Book> bookList = Arrays.asList(
			new Book(book)
		);
		
		when(bookServiceDAO.searchBook(isbn, title, author)).thenReturn(bookList);
		assertEquals(bookList, bookService.searchBook(isbn, title, author));
		
		when(bookServiceDAO.searchBook(isbn, null, null)).thenReturn(bookList);
		assertEquals(bookList, bookService.searchBook(isbn, null, null));
		
		when(bookServiceDAO.searchBook(null, title, null)).thenReturn(bookList);
		assertEquals(bookList, bookService.searchBook(null, title, null));
		
		when(bookServiceDAO.searchBook(null, null, author)).thenReturn(bookList);
		assertEquals(bookList, bookService.searchBook(null, null, author));
		
		
	}
	
	@Test(expected=FieldValueTooSmallException.class)
	public void searchBookFieldValueTooSmallTest() {
		int isbn = 123;
		String title = "t";
		String author = "a";
		
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		book.setAuthor(author);
		book.setPrice(90.7f);
		book.setAvailable_copies(1);
		
		List<Book> bookList = Arrays.asList(
			new Book(book)
		);
		
		when(bookServiceDAO.searchBook(null, title, author)).thenReturn(bookList);
		assertNull(bookService.searchBook(null, title, author));		//field value too small, so exception will be expected
	}
	
	@Test(expected=InvalidInputException.class)
	public void searchBookInvalidInputTest() {
		when(bookServiceDAO.searchBook(null, null, null)).thenReturn(null);
		assertNull(bookService.searchBook(null, null, null));			// since at least one parameter should not be null
	}
	
	@Test
	public void searchForMediaCoverTest() {
		String title = "book title";
		String title2 = "this should be in post body";		// for case if title is in post's body
		Post post = new Post();
		post.setTitle("this is the book title");
		post.setBody("The title of the book is this should be in post body");
		
		List<Post> posts = Arrays.asList(post);
		List<String> result = Arrays.asList(post.getTitle());
		
		when(bookServiceDAO.getMediaCoverPosts()).thenReturn(posts);
		assertEquals(result, bookService.searchMediaCover(title));
		
		when(bookServiceDAO.getMediaCoverPosts()).thenReturn(posts);
		assertEquals(result, bookService.searchMediaCover(title2));
		
		when(bookServiceDAO.getMediaCoverPosts()).thenReturn(posts);
		assertEquals(0, bookService.searchMediaCover("not a title").size()); // should not return any matching title
	}

}