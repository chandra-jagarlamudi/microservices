/**
 * 
 */
package com.ragas.boot.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ragas.boot.rest.domain.Book;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class BookServiceImpl implements BookService {
	
	List<Book> booksList = createBooks();

	@Override
	public List<Book> findAllBooks() {
		return booksList;
	}

	@Override
	public Book findBookById(Long bookId) {
		return null;
	}

	@Override
	public Book createBook(Book book) {
		booksList.add(book);
		return book;
	}

	@Override
	public void deleteBook(Long bookId) {

	}

	@Override
	public Book updateBook(Book book, Long bookId) {
		return book;
	}

	@Override
	public Book updateBook(Map<String, String> updates, Long bookId) {
		return null;
	}
	
	private List<Book> createBooks(){
		List<Book> booksList = new ArrayList<>();
		booksList.add(new Book(new Long("1"), "Paramahansa Yogananda", "Autobiography of a Yogi"));
		booksList.add(new Book(new Long("2"), "Robert T. Kiyosaki", "Rich Dad, Poor Dad"));
		return booksList;
	}

}
