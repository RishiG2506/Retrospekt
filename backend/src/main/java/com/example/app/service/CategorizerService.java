package com.example.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.config.CategorizerAgent;
import com.example.app.model.Category;
import com.example.app.model.ContentItemRequest;

@Service
public class CategorizerService {

    private final CategorizerAgent categorizerAgent;

    @Autowired
    public CategorizerService(CategorizerAgent categorizerAgent){
        this.categorizerAgent = categorizerAgent;
    }

    public String categorizeV1(ContentItemRequest tweet){
        return categorizerAgent.categorizeV1(tweet.getContent());
    }

    public Category categorizeV2(ContentItemRequest tweet){
        return categorizerAgent.categorizeV2(tweet.getContent());
    }
}
