package com.example.app.config;

import com.example.app.model.Category;

import dev.langchain4j.service.SystemMessage;

public interface CategorizerAgent {
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
