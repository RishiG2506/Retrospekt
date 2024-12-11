package com.example.app.config;

import java.util.List;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface QueryEnhancedRAGAgent {

    @SystemMessage({"The input text has to be sumarized",
                    "For a good quality summary, you have to generate some prompts/questions related to the text",
                    "These prompts will be provided to the LLMs along with the input text to get the required information for a good summary",
                    "Only return the prompts as a list. Don't include any metadata telling anything about the prompts, or any blank line in between"
    })
    List<String> getQueryPrompts(String text);


    @SystemMessage({
        "Read and analyse the text {{content}}, and answer the queries",
        "Ensure the response is not more than 100 characters in length"
    })
    public String enhancedRAGResponse(@UserMessage String augmentedQuery, @V("content") String content);
}
