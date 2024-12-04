package com.example.app.controller;

import com.example.app.model.Category;
import com.example.app.model.ContentItemRequest;
import com.example.app.repository.TweetRepository;
import com.example.app.service.CategorizerService;
import com.example.app.service.RecommendationService;
import com.example.app.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private CategorizerService categorizerService;

    @Autowired
    private RecommendationService recommendationService;


    @GetMapping
    public List<ContentItemRequest> getAllItems() {
        return tweetRepository.findAll();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<ContentItemRequest> postReq(@RequestBody ContentItemRequest contentItemRequest) {
        System.out.println("Post API 1 Called");
        String summary = summaryService.summary(contentItemRequest);
        contentItemRequest.setContent(summary);
        // Tweet savedTweet = tweetRepository.save(tweet);
        return ResponseEntity.ok(contentItemRequest);
    }

    @PostMapping("/categoryV1")
    public ResponseEntity<String> category(@RequestBody ContentItemRequest contentItemRequest){
        String category = categorizerService.categorizeV1(contentItemRequest);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/categoryV2")
    public ResponseEntity<Category> categoryV2(@RequestBody ContentItemRequest contentItemRequest){
        Category category = categorizerService.categorizeV2(contentItemRequest);
        if(category instanceof Category){
            System.out.println("Enum returned");
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping("/RAG")
    public ResponseEntity<List<String>> getQueries(@RequestBody ContentItemRequest contentItemRequest){
        List<String> recommendations = recommendationService.getRecommendations(contentItemRequest);
        return ResponseEntity.ok(recommendations);
    }
}