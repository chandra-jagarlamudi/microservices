/**
 * 
 */
package com.ragas.boot.rest.service;

import java.util.List;

import com.ragas.boot.rest.domain.Book;

/**
 * @author Chandra Jagarlamudis
 *
 */
public interface BookService {

	List<Book> findAllBooks();

	Book findBookById(Long bookId);

	Book createBook(Book book);

	void deleteBook(Long bookId);

	Book updateBook(Book book, Long bookId);
	
}
