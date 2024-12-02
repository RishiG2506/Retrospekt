package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.config.CategorizerAgent;
import com.example.app.model.Category;
import com.example.app.model.Tweet;

@Service
public class CategorizerService {

    private final CategorizerAgent categorizerAgent;

    public CategorizerService(CategorizerAgent categorizerAgent){
        this.categorizerAgent = categorizerAgent;
    }

    public String categorizeV1(Tweet tweet){
        return categorizerAgent.categorizeV1(tweet.getContent());
    }

    public Category categorizeV2(Tweet tweet){
        return categorizerAgent.categorizeV2(tweet.getContent());
    }
}
