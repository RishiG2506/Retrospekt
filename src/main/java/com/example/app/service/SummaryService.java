package com.example.app.service;

import com.example.app.model.Tweet;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.mistralai.MistralAiChatModel;
import dev.langchain4j.model.mistralai.MistralAiChatModelName;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

//    private String API_KEY;

    private ChatLanguageModel mistralAiModel;
//    private ChatLanguageModel geminiModel;
    private final SummaryAgent agent;


    public SummaryService(@Value("${spring.ai.mistralai.api-key}") String API_KEY){
        mistralAiModel = MistralAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName(MistralAiChatModelName.MISTRAL_MEDIUM_LATEST)
                .logRequests(true)
                .logResponses(true)
                .build();
        agent = AiServices.create(SummaryAgent.class, mistralAiModel);
    }

    public String summary(Tweet tweet){
        String result = agent.chat(tweet.getContent());
        return result;
    }
}
