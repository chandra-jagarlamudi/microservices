/**
 * 
 */
package com.ragas.boot.rest.persistence.dao;

import org.springframework.data.repository.CrudRepository;

import com.ragas.boot.rest.persistence.model.Book;

/**
 * @author Chandra Jagarlamudi
 *
 */
public interface BookRepository extends CrudRepository<Book, Long> {

}
