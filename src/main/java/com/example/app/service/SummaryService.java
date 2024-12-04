package com.example.app.service;

import com.example.app.config.QueryEnhancedRAGAgent;
import com.example.app.config.SummaryAgent;
import com.example.app.model.ContentItemRequest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    private final SummaryAgent summaryAgent;
    private final QueryEnhancedRAGAgent queryEnhancedRAGAgent;

    @Autowired
    public SummaryService(SummaryAgent summaryAgent, QueryEnhancedRAGAgent queryEnhancedRAGAgent){
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        this.summaryAgent = summaryAgent;
        this.queryEnhancedRAGAgent = queryEnhancedRAGAgent;
    }

    public String summary(ContentItemRequest contentItemRequest){
        String ragAddedContext = getRAGAddedContext(contentItemRequest);
        String result = summaryAgent.chat(contentItemRequest.getContent(), ragAddedContext);
        return result;
    }

    public String getRAGAddedContext(ContentItemRequest contentItemRequest){
        List<String> prompts = queryEnhancedRAGAgent.getQueryPrompts(contentItemRequest.getContent());
        String enhancedRAGContext = "";
        for (String prompt: prompts){
            String response = queryEnhancedRAGAgent.enhancedRAGResponse(prompt, contentItemRequest.getContent());
            enhancedRAGContext += "Query: " + prompt + "\n" + "Response:" + response + "\n";
        }
        System.out.println(enhancedRAGContext);
        return enhancedRAGContext;
    }

}
