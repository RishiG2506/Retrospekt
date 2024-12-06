package com.example.app.config;

import java.util.List;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface RecommendationRAGAgent {
    @UserMessage({"You are a recommendation agent",
                    "You will be provided some input text and the category/genre it belongs to",
                    "Provide links/urls to some relevant and similar websites/articles/images that maybe relevant to the text",
                    "Only return the urls as a list of strings. Don't generate any other metadata about the result or a blank string in between",
                    "The only strings in your response must be the urls themselves, no other text should be present",
                    "Ensure that there are no duplicate urls",
                    "The input text is {{content}}",
                    "The category is {{category}}"
    })
    public List<String> recommend(@V("content") String content, @V("category") String category);
}
