/**
 * 
 */
package com.ragas.boot.rest.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Preconditions;
import com.ragas.boot.rest.exception.BookNotFoundException;
import com.ragas.boot.rest.persistence.dao.BookRepository;
import com.ragas.boot.rest.persistence.model.Book;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	@Override
	public Book findBookById(final Long bookId) {
		return Optional.ofNullable(bookRepository.findOne(bookId))
				.orElseThrow(() -> new BookNotFoundException("Book not found. ID: " + bookId));
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Book createBook(final Book book) {
		return bookRepository.save(book);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteBook(final Long bookId) {
		bookRepository.delete(bookId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Book updateBook(final Map<String, String> updates, final Long bookId) {
		final Book book = findBookById(bookId);
		updates.keySet().forEach(key -> {
			switch (key) {
			case "author":
				book.setAuthor(updates.get(key));
				break;
			case "title":
				book.setTitle(updates.get(key));
			}
		});
		return bookRepository.save(book);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Book updateBook(final Book book, final Long bookId) {
		Preconditions.checkNotNull(book);
		Preconditions.checkState(book.getId() == bookId);
		Preconditions.checkNotNull(bookRepository.findOne(bookId));
		return bookRepository.save(book);
	}

}
