package com.example.app.config;

import dev.langchain4j.service.SystemMessage;

public interface SummaryAgent {
//    @SystemMessage("Summarize any input text provided in maximum of 100 words")
    @SystemMessage({"You are a summarizing agent", 
                    "Whatever text that is given as input to you, return a summary in not more than 100 words", 
                    "Do not use any third person mode of speech while summarizing, use the mode which the original author has used", 
                    "You can also add links to any relevant articles/blogs on the internet"})
    String chat(String userPrompt);

}
