package com.example.app.service;

import com.example.app.config.QueryEnhancedRAGAgent;
import com.example.app.config.SummaryAgent;
import com.example.app.model.Tweet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    private final SummaryAgent summaryAgent;
    private final QueryEnhancedRAGAgent queryEnhancedRAGAgent;

    public SummaryService(SummaryAgent summaryAgent, QueryEnhancedRAGAgent queryEnhancedRAGAgent){
        this.summaryAgent = summaryAgent;
        this.queryEnhancedRAGAgent = queryEnhancedRAGAgent;
    }

    public String summary(Tweet tweet){
        String ragAddedContext = getRAGAddedContext(tweet);
        String result = summaryAgent.chat(tweet.getContent(), ragAddedContext);
        return result;
    }

    public String getRAGAddedContext(Tweet tweet){
        List<String> prompts = queryEnhancedRAGAgent.getQueryPrompts(tweet.getContent());
        String enhancedRAGContext = "";
        for (String prompt: prompts){
            String response = queryEnhancedRAGAgent.enhancedRAGResponse(prompt, tweet.getContent());
            enhancedRAGContext += "Query: " + prompt + "\n" + "Response:" + response + "\n";
        }
        System.out.println(enhancedRAGContext);
        return enhancedRAGContext;
    }

}
