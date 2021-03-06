/**
 * 
 */
package com.ragas.microservices.multiplication.service;

import com.ragas.microservices.multiplication.domain.Multiplication;
import com.ragas.microservices.multiplication.domain.MultiplicationResultAttempt;

/**
 * @author Chandra Jagarlamudi
 *
 */
public interface MultiplicationService {

	/**
     * Creates a Multiplication object with two randomly-generated factors
     * between 11 and 99.
     *
     * @return a Multiplication object with random factors
     */
    Multiplication createRandomMultiplication();

    /**
     * @return true if the attempt matches the result of the
     *         multiplication, false otherwise.
     */
    boolean checkAttempt(final MultiplicationResultAttempt resultAttempt);

}
