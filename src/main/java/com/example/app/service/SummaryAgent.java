package com.example.app.service;

import dev.langchain4j.service.SystemMessage;

public interface SummaryAgent {
    @SystemMessage("Summarize any input text provided in maximum of 100 words")
    String chat(String userPrompt);
}
