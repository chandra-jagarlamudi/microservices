/**
 * 
 */
package com.ragas.microservices.multiplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragas.microservices.multiplication.domain.Multiplication;
import com.ragas.microservices.multiplication.service.MultiplicationService;

/**
 * @author Chandra Jagarlamudi
 *
 */

@RestController
@RequestMapping("/multiplications")
final class MultiplicationController {

	MultiplicationService multiplicationService;

	@Autowired
	public MultiplicationController(MultiplicationService multiplicationService) {
		this.multiplicationService = multiplicationService;
	}

	@GetMapping("/random")
	Multiplication getRandomMultiplication() {
		return multiplicationService.createRandomMultiplication();

	}


}
