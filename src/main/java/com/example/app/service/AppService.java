package com.example.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AppService.class);

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

        for(String url: recommendations){
            try{
                RelevantLinkId relevantLinkID = new RelevantLinkId(savedItem.getId(), url);
                if (relevantLinkRepository.existsById(relevantLinkID))
                    continue;

                RelevantLink relevantLink = relevantLinkRepository.findById(relevantLinkID)
                        .orElse(new RelevantLink(relevantLinkID, savedItem));

                if (!savedItem.getRelevantLinks().contains(relevantLink)) {
                    savedItem.addRelevantLink(relevantLink);
                }
            } catch(Exception e){
                logger.info(e.getMessage());
            }
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
