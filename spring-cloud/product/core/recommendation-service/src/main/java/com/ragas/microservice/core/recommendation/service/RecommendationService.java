/**
 * 
 */
package com.ragas.microservice.core.recommendation.service;

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

import com.ragas.microservice.core.recommendation.model.Recommendation;
import com.ragas.microservice.core.recommendation.service.util.SetProcessingTimeBean;

/**
 * @author Chandra Jagarlamud
 *
 */
@RestController
@RequestMapping("/recommendation")
public class RecommendationService {
	private static final Logger LOG = LoggerFactory.getLogger(RecommendationService.class);

    @Autowired
    private SetProcessingTimeBean setProcessingTimeBean;

    /**
     * Sample usage: curl $HOST:$PORT/recommendation?productId=1
     *
     * @param productId
     * @return
     */
    @GetMapping
    public List<Recommendation> getRecommendations(
            @RequestParam(value = "productId",  required = true) int productId) {

        int pt = setProcessingTimeBean.calculateProcessingTime();
        LOG.info("/recommendation called, processing time: {}", pt);

        sleep(pt);

        List<Recommendation> list = new ArrayList<>();
        list.add(new Recommendation(productId, 1, "Author 1", 1, "Content 1"));
        list.add(new Recommendation(productId, 2, "Author 2", 2, "Content 2"));
        list.add(new Recommendation(productId, 3, "Author 3", 3, "Content 3"));

        LOG.info("/recommendation response size: {}", list.size());

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
