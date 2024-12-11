package com.example.app.model;

public enum Category {
    TECHNOLOGY,
    EDUCATION_AND_CAREER,
    HEALTH_AND_WELLNESS,
    BUSINESS_AND_FINANCE,
    ENTERTAINMENT_AND_LIFESTYLE,
    NEWS_AND_POLITICS,
    HUMOR_AND_MEMES,
    OTHERS;

    public static String getCategoryString(Category category) {
        if (category == null) {
            return null;
        }
        return switch (category) {
            case TECHNOLOGY -> "Technology";
            case HEALTH_AND_WELLNESS -> "Health and Wellness";
            case EDUCATION_AND_CAREER -> "Education and Career";
            case ENTERTAINMENT_AND_LIFESTYLE -> "Entertainment and Lifestyle";
            case NEWS_AND_POLITICS -> "News and Politics";
            case HUMOR_AND_MEMES -> "Humor and Memes";
            case OTHERS-> "Others";
            default -> "Unknown Category";
        };
    }
} 
