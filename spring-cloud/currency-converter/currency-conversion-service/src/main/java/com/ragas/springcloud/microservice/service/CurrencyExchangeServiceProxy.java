package com.ragas.springcloud.microservice.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ragas.springcloud.microservice.persistence.model.CurrencyConverter;

/**
 * @author Chandra Jagarlamudi
 *
 */

@FeignClient(name="currency-exchange-service")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	CurrencyConverter retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
