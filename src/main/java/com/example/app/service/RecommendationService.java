package com.example.app.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.example.app.config.RecommendationAgent;
import com.example.app.config.RecommendationRAGAgent;
import com.example.app.model.ContentItemRequest;

@Service
public class RecommendationService {

    private final RecommendationAgent recommendationAgent;
    private final RecommendationRAGAgent recommendationRAGAgent;

    public RecommendationService(RecommendationAgent recommendationAgent, RecommendationRAGAgent recommendationRAGAgent){
        this.recommendationAgent = recommendationAgent;
        this.recommendationRAGAgent = recommendationRAGAgent;
    }

    public List<String> getRecommendations(ContentItemRequest tweet){
        String category = "News and Politics";
        List<String> ragList = recommendationRAGAgent.recommend(tweet.getContent(), category);
        List<String> nonragList = recommendationAgent.recommend(tweet.getContent(), category);
        return Stream.concat(ragList.stream(), nonragList.stream())
                 .collect(Collectors.toList());
    }
}
