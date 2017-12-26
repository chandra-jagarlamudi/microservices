package com.ragas.springcloud.microservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ragas.springcloud.microservice.persistence.model.ExchangeValue;

/**
 * @author Chandra Jagarlamudi
 *
 */

@Repository
public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromAndTo(String from, String to);
}
