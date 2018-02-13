package com.ragas.microservices.support.discovery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaServer
@RestController
public class EurekaServer {

	@Autowired
	private DiscoveryClient discoveryClient;

	@GetMapping("/service-instances/{applicationName}")
	public ResponseEntity<List<ServiceInstance>> serviceInstancesByApplicationName(
			@PathVariable String applicationName) {
		return new ResponseEntity<List<ServiceInstance>>(this.discoveryClient.getInstances(applicationName),
				HttpStatus.OK);
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer.class, args);
	}
}
