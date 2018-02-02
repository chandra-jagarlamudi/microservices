package com.ragas.microservice.support.edge;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
@EnableZuulProxy
public class ZuulServer {
	public static void main(String[] args) {
		new SpringApplicationBuilder(ZuulServer.class).web(true).run(args);
	}
}
