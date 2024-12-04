package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.repository.ContentItemRepository;
import com.example.app.repository.RelevantLinkRepository;

@Service
public class AppService {

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private CategorizerService categorizerService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private ContentItemRepository contentItemRepository;

    @Autowired
    private RelevantLinkRepository relevantLinkRepository;
    
}
