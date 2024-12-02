package com.example.app.service;

import com.example.app.config.SummaryAgent;
import com.example.app.model.Tweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

    private final SummaryAgent agentV2;

    public SummaryService(SummaryAgent summaryAgent){
        this.agentV2 = summaryAgent;
    }

    public String summary(Tweet tweet){
        String result = agentV2.chat(tweet.getContent());
        return result;
    }

}
