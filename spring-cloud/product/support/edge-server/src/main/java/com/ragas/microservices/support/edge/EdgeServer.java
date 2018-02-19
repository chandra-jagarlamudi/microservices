package com.ragas.microservices.support.edge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.ragas.microservices.support.edge.filter.pre.ZuulLoggingFilter;

@EnableZuulProxy
@SpringBootApplication
public class EdgeServer {

	public static void main(String[] args) {
		SpringApplication.run(EdgeServer.class, args);
	}
	
	@Bean
	  public ZuulLoggingFilter productPreFilter() {
	    return new ZuulLoggingFilter();
	  }
}
