package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;
import com.example.bookstore.model.User;
import com.example.bookstore.model.UserRepository;






@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("Save a few categories");
			crepository.save(new Category("autobiography"));
			crepository.save(new Category("fantasy"));
			crepository.save(new Category("romance"));
			
			log.info("Save a few books");
			brepository.save(new Book("Becoming", "Michelle Obama", 2018, "9780241334140", 22.50, crepository.findByName("autobiography").get(0)));
			brepository.save(new Book("Harry Potter and the Philosopher's stone", "J.K. Rowling", 1997, "9781408845646", 25.00, crepository.findByName("fantasy").get(0)));
			
			log.info("Save a few users");
			User user1 = new User("user", "$2y$12$tqUpUJNy7bA/thNlipiKWe1N0FRptMT0yE9mW0DeOMN27eKOU8vAS", "user@email.com", "USER");
			User user2 = new User("admin", "$2y$12$SFgoUMobWWadz4ifbGoEEu/Pz8pIwjhOsp8o8YDH9/KGvfo6vOCxS", "admin@email.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("Fetch all books");
			for (Book book: brepository.findAll()) {
				log.info(book.toString());
			}

		};
	}

}
