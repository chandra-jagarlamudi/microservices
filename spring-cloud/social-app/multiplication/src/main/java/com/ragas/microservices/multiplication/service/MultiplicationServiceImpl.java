/**
 * 
 */
package com.ragas.microservices.multiplication.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragas.microservices.multiplication.domain.Multiplication;
import com.ragas.microservices.multiplication.domain.MultiplicationResultAttempt;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Service
final class MultiplicationServiceImpl implements MultiplicationService {

	private RandomGeneratorService randomGeneratorService;

	@Autowired
	public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService) {
		this.randomGeneratorService = randomGeneratorService;
	}

	@Override
	public Multiplication createRandomMultiplication() {
		return new Multiplication(randomGeneratorService.generateRandomFactor(),
				randomGeneratorService.generateRandomFactor(), new Date().toString());
	}

	@Override
	public boolean checkAttempt(MultiplicationResultAttempt resultAttempt) {
		return resultAttempt.getResultAttempt() == resultAttempt.getMultiplication().getFactorA()
				* resultAttempt.getMultiplication().getFactorB();
	}

}
