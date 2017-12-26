package com.ragas.springcloud.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragas.springcloud.microservice.Configuration;

/**
 * @author Chandra Jagarlamudi
 *
 */

@RestController
public class LimitsConfigurationController {
	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration getLimitsFromConfigurations() {
		LimitConfiguration limitConfiguration = new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
		return limitConfiguration;
	}

}
