package com.ragas.springcloud.microservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ragas.springcloud.microservice.persistence.model.ExchangeValue;
import com.ragas.springcloud.microservice.persistence.repository.CurrencyExchangeRepository;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Component
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepository;

	@Override
	public ExchangeValue findByFromAndTo(String from, String to) {
		return currencyExchangeRepository.findByFromAndTo(from, to);
	}

}
