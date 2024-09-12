package com.example.app.controller;

import com.example.app.model.Tweet;
import com.example.app.repository.TweetRepository;
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

    @Autowired
    private MistralAiChatModel chatModel;


    @GetMapping
    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Tweet> createTweet(@RequestBody Tweet tweet) {
        String summary = summarizeTweet(tweet);
        tweet.setContent(summary);
        Tweet savedTweet = tweetRepository.save(tweet);
        return ResponseEntity.ok(savedTweet);
    }

    String summarizeTweet(Tweet tweet){
        String prompt = "Summarize the following text in less than 20 words:\n " + tweet.getContent();
        String result = chatModel.call(prompt);
        System.out.println(result);
        return result;
    }
}
