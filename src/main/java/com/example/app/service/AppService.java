package com.example.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.model.Category;
import com.example.app.model.ContentItem;
import com.example.app.model.ContentItemRequest;
import com.example.app.model.RelevantLink;
import com.example.app.model.RelevantLinkId;
import com.example.app.repository.ContentItemRepository;
import com.example.app.repository.RelevantLinkRepository;

@Service
public class AppService {

    @Autowired
    private SummaryService summaryService;

    @Autowired
    private CategorizerService categorizerService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private ContentItemRepository contentItemRepository;

    @Autowired
    private RelevantLinkRepository relevantLinkRepository;


    public ContentItem service(ContentItemRequest contentItemRequest){
        Category category = categorizerService.categorizeV2(contentItemRequest);
        String categoryString = Category.getCategoryString(category);

        String summary = summaryService.summary(contentItemRequest);

        List<String> recommendations = recommendationService.recommendations(contentItemRequest, categoryString);

        ContentItem contentItem = new ContentItem(contentItemRequest.getUrl(), categoryString, contentItemRequest.getAuthor(), summary);

        ContentItem savedItem = contentItemRepository.save(contentItem);
        System.out.println("URLS:");
        for(String url: recommendations){
            System.out.println(url);
            RelevantLinkId relevantLinkID = new RelevantLinkId(savedItem.getId(), url);
            if (relevantLinkRepository.existsById(relevantLinkID))
                continue;

            RelevantLink relevantLink = relevantLinkRepository.findById(relevantLinkID)
                      .orElse(new RelevantLink(relevantLinkID, savedItem));

            if (!savedItem.getRelevantLinks().contains(relevantLink)) {
                savedItem.addRelevantLink(relevantLink);
            }

            // RelevantLink relevantLink = new RelevantLink(relevantLinkID, savedItem);
            // savedItem.addRelevantLink(relevantLink);
        }

        return contentItemRepository.save(savedItem);
    }

    public List<ContentItem> getAllItems(){
        return contentItemRepository.findAll();
    }


    public List<String> getRelevantLinksByContentItemId(Long contentItemId) {
        return relevantLinkRepository.findUrlsByContentItemId(contentItemId);
    }

    public List<ContentItem> getItemsByCategory(String Category){
        return contentItemRepository.findByCategory(Category);
    }


}
