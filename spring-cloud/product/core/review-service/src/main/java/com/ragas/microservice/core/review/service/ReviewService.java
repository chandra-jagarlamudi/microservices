/**
 * 
 */
package com.ragas.microservice.core.review.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ragas.microservice.core.review.model.Review;
import com.ragas.microservice.core.review.service.util.SetProcessingTimeBean;

/**
 * @author Chandra Jagarlamudi
 *
 */
@RestController
@RequestMapping("/review")
public class ReviewService {
	private static final Logger LOG = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private SetProcessingTimeBean setProcessingTimeBean;

    /**
     * Sample usage: curl $HOST:$PORT/review?productId=1
     *
     * @param productId
     * @return
     */
    @GetMapping
    public List<Review> getReviews(
            @RequestParam(value = "productId",  required = true) int productId) {
        int pt = setProcessingTimeBean.calculateProcessingTime();
        LOG.info("/reviews called, processing time: {}", pt);
        sleep(pt);
        List<Review> list = new ArrayList<>();
        list.add(new Review(productId, 1, "Author 1", "Subject 1", "Content 1"));
        list.add(new Review(productId, 2, "Author 2", "Subject 2", "Content 2"));
        list.add(new Review(productId, 3, "Author 3", "Subject 3", "Content 3"));

        LOG.info("/reviews response size: {}", list.size());
        return list;
    }

    private void sleep(int pt) {
        try {
            Thread.sleep(pt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sample usage:
     *
     *  curl "http://localhost:10002/set-processing-time?minMs=1000&maxMs=2000"
     *
     * @param minMs
     * @param maxMs
     */
    @PostMapping("/set-processing-time")
    public void setProcessingTime(
        @RequestParam(value = "minMs", required = true) int minMs,
        @RequestParam(value = "maxMs", required = true) int maxMs) {
        LOG.info("/set-processing-time called: {} - {} ms", minMs, maxMs);
        setProcessingTimeBean.setDefaultProcessingTime(minMs, maxMs);
    }
}
