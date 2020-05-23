package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bookstore.bean.Book;

@Configuration
public class AppConfig{
	
	@Bean
	public Book getBook() {
		return new Book();
	}
}