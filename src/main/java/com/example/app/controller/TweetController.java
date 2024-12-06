package com.example.app.controller;

import com.example.app.model.Category;
import com.example.app.model.ContentItem;
import com.example.app.model.ContentItemRequest;
import com.example.app.repository.ContentItemRepository;
import com.example.app.service.AppService;
import com.example.app.service.CategorizerService;
import com.example.app.service.RecommendationService;
import com.example.app.service.SummaryService;

import dev.ai4j.openai4j.chat.Content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class TweetController {

    @Autowired
    private AppService appService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<ContentItem>> getAllItems() {
        List<ContentItem> items = appService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @CrossOrigin
    @GetMapping("/{contentItemId}/links")
    public ResponseEntity<List<String>> getRelevantLinksByContentItemId(@PathVariable Long contentItemId) {
        List<String> links = appService.getRelevantLinksByContentItemId(contentItemId);
        return ResponseEntity.ok(links);
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<ContentItem> processContent(@RequestBody ContentItemRequest contentItemRequest){
        System.out.println("Main Post API Called");
        ContentItem savedItem = appService.service(contentItemRequest);
        return ResponseEntity.ok(savedItem);
    }

    // @PostMapping("/categoryV1")
    // public ResponseEntity<String> category(@RequestBody ContentItemRequest contentItemRequest){
    //     String category = categorizerService.categorizeV1(contentItemRequest);
    //     return ResponseEntity.ok(category);
    // }

    // @PostMapping("/categoryV2")
    // public ResponseEntity<Category> categoryV2(@RequestBody ContentItemRequest contentItemRequest){
    //     Category category = categorizerService.categorizeV2(contentItemRequest);
    //     if(category instanceof Category){
    //         System.out.println("Enum returned");
    //     }
    //     return ResponseEntity.ok(category);
    // }

}