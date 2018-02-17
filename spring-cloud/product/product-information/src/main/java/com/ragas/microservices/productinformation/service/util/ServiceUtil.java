/**
 * 
 */
package com.ragas.microservices.productinformation.service.util;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Chandra Jagarlamudi
 *
 */

@Component
public class ServiceUtil {

	private static final Logger LOG = LoggerFactory.getLogger(ServiceUtil.class);

	@Autowired
	private LoadBalancerClient loadBalancer;

	/**
	 * This method takes serviceId and fallbackUri and returns the load balanced
	 * service URI fetched based in the service Id. In the case of errors returns
	 * the fallback URI
	 * 
	 * @param serviceId
	 * @param fallbackUri
	 * @return
	 */
	public URI getServiceUrl(String serviceId, String fallbackUri) {
		URI uri = null;
		try {
			ServiceInstance instance = loadBalancer.choose(serviceId);
			uri = instance.getUri();
			LOG.debug("Resolved serviceId '{}' to URL '{}'.", serviceId, uri);
		} catch (RuntimeException e) {
			// Eureka not available, using fallback
			uri = URI.create(fallbackUri);
			LOG.warn("Failed to resolve serviceId '{}'. returning Fallback URI '{}'.", serviceId, uri);
		}
		return uri;
	}

	public <T> ResponseEntity<T> createOkResponse(T body) {
		return createResponse(body, HttpStatus.OK);
	}

	public <T> ResponseEntity<T> createResponse(T body, HttpStatus httpStatus) {
		return new ResponseEntity<>(body, httpStatus);
	}
}
