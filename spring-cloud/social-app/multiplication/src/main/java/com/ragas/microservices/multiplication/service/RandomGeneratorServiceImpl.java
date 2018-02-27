/**
 * 
 */
package com.ragas.microservices.multiplication.service;

import java.util.Random;

import org.springframework.stereotype.Service;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Service
final class RandomGeneratorServiceImpl implements RandomGeneratorService {

	final static int MINIMUM_FACTOR = 11;
	final static int MAXIMUM_FACTOR = 99;


	@Override
	public int generateRandomFactor() {
		return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
	}

}
