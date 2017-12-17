/**
 * 
 */
package com.ragas.boot.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Chandra Jagarlamudi
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2712722309091827178L;

	BookNotFoundException(String message) {
		super(message);
	}

}
