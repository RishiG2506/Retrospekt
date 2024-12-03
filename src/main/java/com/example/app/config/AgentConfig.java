package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import dev.langchain4j.service.AiServices;

@Configuration
public class AgentConfig {

    @Bean
    public ChatLanguageModel openAILanguageModel(){
        return OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .logRequests(true)
                .logResponses(true)
                .build();
    }

    @Bean
    public SummaryAgent summaryAgent(){
        return AiServices.create(SummaryAgent.class, openAILanguageModel());
    }

    @Bean
    public CategorizerAgent categorizerAgent(){
        return AiServices.create(CategorizerAgent.class, openAILanguageModel());
    }

    @Bean
    public QueryEnhancedRAGAgent queryEnhancedRAGAgent(){
        return AiServices.create(QueryEnhancedRAGAgent.class, openAILanguageModel());
    }

}
