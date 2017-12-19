package com.ragas.boot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ragas.boot.rest.persistence.dao.BookRepository;
import com.ragas.boot.rest.persistence.model.Book;

@SpringBootApplication
public class BookServiceApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookrepository;

	public static void main(String[] args) {
		SpringApplication.run(BookServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// fetch all books from DB
		System.out.println("Books found with findAll():");
		System.out.println("-------------------------------");
		for (Book book : bookrepository.findAll()) {
			System.out.println(book);
		}
	}
}
