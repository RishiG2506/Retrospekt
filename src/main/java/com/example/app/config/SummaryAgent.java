package com.example.app.config;

import java.util.List;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface SummaryAgent {
    @SystemMessage({"You are a summarizing agent", 
                    "Return a summary of the text provided as User input in not more than 100 words",
                    "Do not use any third person mode of speech while summarizing, use the mode which the original author has used", 
                    "Take assistance from some enhanced context given in query-response format: {{RAGAddedContext}} and aggregate the results"
                })
    String chat(@UserMessage String content, @V("RAGAddedContext") String ragAddedContext);

}
