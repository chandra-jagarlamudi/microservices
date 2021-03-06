package com.ragas.springcloud.microservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ragas.springcloud.microservice.persistence.model.CurrencyConverter;
import com.ragas.springcloud.microservice.service.CurrencyExchangeServiceProxy;

/**
 * @author Chandra Jagarlamudi
 *
 */

@RestController
public class CurrencyConverterController {

	@Autowired
	private CurrencyExchangeServiceProxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverter convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		/**
		 * START - CALLING OTHER MICROSERVICE, We are calling currency-exchange-service
		 * 
		 * Feign - simplifies the proces of calling other microservices
		 */
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConverter> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConverter.class, uriVariables);
		CurrencyConverter response = responseEntity.getBody();
		//END - CALLING OTHER MICROSERVICE

		return new CurrencyConverter(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

	/**
	 * Feign way of calling other microservices
	 */
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConverter convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		CurrencyConverter response = proxy.retrieveExchangeValue(from, to);
		return new CurrencyConverter(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}

}
