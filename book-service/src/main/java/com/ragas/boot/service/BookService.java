/**
 * 
 */
package com.ragas.boot.service;

import java.util.List;

import com.ragas.boot.domain.Book;

/**
 * @author Chandra Jagarlamudi
 *
 */
public interface BookService {

	public List<Book> findAllBooks();

	public Book findBookById(Long bookId);

	public Book createBook(Book book);

	public void deleteBook(Long bookId);

	public Book updateBook(Book book, Long bookId);

}
