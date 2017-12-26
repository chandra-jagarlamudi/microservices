package com.ragas.springcloud.microservice.service;

import org.springframework.stereotype.Service;

import com.ragas.springcloud.microservice.persistence.model.ExchangeValue;

/**
 * @author Chandra Jagarlamudi
 *
 */
@Service
public interface CurrencyExchangeService {
	ExchangeValue findByFromAndTo(String from, String to);
}
