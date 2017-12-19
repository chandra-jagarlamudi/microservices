package com.ragas.boot.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ragas.boot.rest.persistance.dao.RatingRepository;
import com.ragas.boot.rest.persistance.model.Rating;

@SpringBootApplication
public class RatingServiceApplication implements CommandLineRunner {

	@Autowired
	RatingRepository ratingRepository;

	public static void main(String[] args) {
		SpringApplication.run(RatingServiceApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// fetch all ratings
		System.out.println("Ratings found with findAll():");
		System.out.println("-------------------------------");
		for (Rating rating : ratingRepository.findAll()) {
			System.out.println(rating);
		}
	}
}
