/**
 * 
 */
package com.ragas.boot.gateway;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.ErrorPageRegistrar;
import org.springframework.boot.web.servlet.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class ErrorPageConfiguration implements ErrorPageRegistrar {
	 @Override
	    public void registerErrorPages(ErrorPageRegistry registry) {
	        registry.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/home/index.html"));
	}

}