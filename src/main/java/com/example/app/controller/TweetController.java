package com.example.app.controller;

import com.example.app.model.Category;
import com.example.app.model.Tweet;
import com.example.app.repository.TweetRepository;
import com.example.app.service.CategorizerService;
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


    @GetMapping
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Tweet> postReq(@RequestBody Tweet tweet) {
        System.out.println("Post API 1 Called");
        String summary = summaryService.summary(tweet);
        tweet.setContent(summary);
        Tweet savedTweet = tweetRepository.save(tweet);
        return ResponseEntity.ok(savedTweet);
    }

    @PostMapping("/categoryV1")
    public ResponseEntity<String> category(@RequestBody Tweet tweet){
        String category = categorizerService.categorizeV1(tweet);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/categoryV2")
    public ResponseEntity<Category> categoryV2(@RequestBody Tweet tweet){
        Category category = categorizerService.categorizeV2(tweet);
        if(category instanceof Category){
            System.out.println("Enum returned");
        }
        return ResponseEntity.ok(category);
    }
}