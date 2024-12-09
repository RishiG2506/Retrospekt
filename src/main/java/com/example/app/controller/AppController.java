package com.example.app.controller;

import com.example.app.model.ContentItem;
import com.example.app.model.ContentItemRequest;
import com.example.app.service.AppService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

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
        ContentItem savedItem = appService.service(contentItemRequest);
        logger.info("Content Saved into Database");
        return ResponseEntity.ok(savedItem);
    }

    @CrossOrigin
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ContentItem>> getContentItemsByCategory(@PathVariable String category){
        List<ContentItem> contentItems = appService.getItemsByCategory(category);
        return ResponseEntity.ok(contentItems);
    }

}