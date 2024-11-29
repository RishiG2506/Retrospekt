package com.example.app.service;

import com.example.app.model.Category;

import dev.langchain4j.service.SystemMessage;

public interface SummaryAgent {
//    @SystemMessage("Summarize any input text provided in maximum of 100 words")
    @SystemMessage({"You are a summarizing agent", "Whatever text that is given as input to you, return a summary in not more than 100 words", "Do not use any third person mode of speech while summarizing, use the mode which the original author has used", "You can also add links to any relevant articles/blogs on the internet"})
    String chat(String userPrompt);

    @SystemMessage(
        {
        "Your task is to categorize the provided input into one of the provided categories",
        "\"Technology\", \"Science\", \"Education\", \"Health & Wellness\", \"Business & Finance\", \"Entertainment\", \"Politics\", \"Environment & Climate\", \"Sports\", \"Culture & Society\", \"Lifestyle\", \"History\", \"Art & Literature\", \"Humor & Memes\", \"News\", \"Personal Development\", \"Technology in Society\", \"Travel & Exploration\", \"Food & Culinary Arts\", \"Philosophy & Thought\", \"DIY & Crafts\", \"Space & Astronomy\", \"Economy & Policy\", \"Community & Local News\", \"Others\"",
        "Only return the string of the name of the category"
        })
    String categorizeV1(String userPrompt);

    @SystemMessage(
        {
        "Your task is to categorize the provided input into one of the available categories",
        "\"Technology\", \"Education and Career\", \"Health and Wellness\", \"Business and Finance\", \"Entertainment and Lifestyle\", \"News and Politics\", \"Humor and Memes\", \"Others\""
        })
    Category categorizeV2(String userPrompt);

}
