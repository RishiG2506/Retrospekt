package com.example.app.controller;

import com.example.app.model.Tweet;
import com.example.app.repository.TweetRepository;
import com.example.app.service.SummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetRepository tweetRepository;

//    @Autowired
//    private MistralAiChatModel chatModel;

    @Autowired
    private SummaryService summaryService;


    @GetMapping
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

//    @PostMapping
//    public ResponseEntity<Tweet> createTweet(@RequestBody Tweet tweet) {
//        String summary = summarizeTweet(tweet);
//        tweet.setContent(summary);
//        Tweet savedTweet = tweetRepository.save(tweet);
//        return ResponseEntity.ok(savedTweet);
//    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Tweet> postReq(@RequestBody Tweet tweet) {
        System.out.println("Post API Called");
        String summary = summaryService.summary(tweet);
        tweet.setContent(summary);
        return ResponseEntity.ok(tweet);
    }

//    String summarizeTweet(Tweet tweet){
//        String prompt = "Summarize the following text in less than 20 words:\n " + tweet.getContent();
//        String result = chatModel.call(prompt);
//        System.out.println(result);
//        return result;
//    }
}
