package com.example.app.service;

import com.example.app.model.Category;
import com.example.app.model.Tweet;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.mistralai.MistralAiChatModel;
import dev.langchain4j.model.mistralai.MistralAiChatModelName;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SummaryService {

//    private String API_KEY;

    private ChatLanguageModel mistralAiModel;
//    private ChatLanguageModel geminiModel;
    private final SummaryAgent agent;

    private final SummaryAgent agentV2;

    private ChatLanguageModel openAIModel;


    public SummaryService(@Value("${spring.ai.mistralai.api-key}") String API_KEY){
        mistralAiModel = MistralAiChatModel.builder()
                .apiKey(API_KEY)
                .modelName(MistralAiChatModelName.MISTRAL_MEDIUM_LATEST)
                .logRequests(true)
                .logResponses(true)
                .build();
        agent = AiServices.create(SummaryAgent.class, mistralAiModel);

        openAIModel = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .logRequests(true)
                .logResponses(true)
                .build();
        
        agentV2 = AiServices.create(SummaryAgent.class, openAIModel);
    }

    public String summary(Tweet tweet){
        String result = agentV2.chat(tweet.getContent());
        return result;
    }

    public String categorizeV1(Tweet tweet){
        return agentV2.categorizeV1(tweet.getContent());
    }

    public Category categorizeV2(Tweet tweet){
        return agentV2.categorizeV2(tweet.getContent());
    }
}
