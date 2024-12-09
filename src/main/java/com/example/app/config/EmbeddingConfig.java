package com.example.app.config;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModelName;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.router.LanguageModelQueryRouter;
import dev.langchain4j.rag.query.router.QueryRouter;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;

import static dev.langchain4j.data.document.loader.FileSystemDocumentLoader.loadDocument;

@Configuration
public class EmbeddingConfig {

    private static final Logger logger = LoggerFactory.getLogger(EmbeddingConfig.class);

    private final ChatLanguageModel openAILanguageModel;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    public EmbeddingConfig (ChatLanguageModel openAILanguageModel){
        this.openAILanguageModel = openAILanguageModel;
    }

    @Bean
    public EmbeddingModel embeddingModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey("demo")
                .modelName(OpenAiEmbeddingModelName.TEXT_EMBEDDING_3_SMALL)
                .build();
    }

    @Bean
    public RecommendationRAGAgent recommendationRAGAgent(){

        ContentRetriever educationAndCareerContentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(educationAndCareerEmbeddingStore())
                .embeddingModel(embeddingModel())
                .build();

        ContentRetriever entertainmentAndLifestyleContentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(entertainmentAndLifestyleEmbeddingStore())
                .embeddingModel(embeddingModel())
                .build();

        ContentRetriever healthAndWellnessContentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(healthAndWellnessEmbeddingStore())
                .embeddingModel(embeddingModel())
                .build();

        ContentRetriever humorAndMemesContentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(humorAndMemesEmbeddingStore())
                .embeddingModel(embeddingModel())
                .build();

        ContentRetriever newsAndPoliticsContentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(newsAndPoliticsEmbeddingStore())
                .embeddingModel(embeddingModel())
                .build();

        ContentRetriever technologyContentRetriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(technologyEmbeddingStore())
                .embeddingModel(embeddingModel())
                .maxResults(4)
                .build();

        
        Map<ContentRetriever, String> retrieverToDescription = new HashMap<>();
        retrieverToDescription.put(educationAndCareerContentRetriever, "Popular links related to Education and Career");
        retrieverToDescription.put(entertainmentAndLifestyleContentRetriever, "Popular links related to Entertainment and Lifestyle");
        retrieverToDescription.put(healthAndWellnessContentRetriever, "Popular links related to Health and Wellness");
        retrieverToDescription.put(humorAndMemesContentRetriever, "Popular links related to Humor and Memes");
        retrieverToDescription.put(newsAndPoliticsContentRetriever, "Popular links related to News and Politics");
        retrieverToDescription.put(technologyContentRetriever, "Popular links related to Technology");
        QueryRouter queryRouter = new LanguageModelQueryRouter(openAILanguageModel, retrieverToDescription);

        RetrievalAugmentor retrievalAugmentor = DefaultRetrievalAugmentor.builder()
                .queryRouter(queryRouter)
                .build();

        return AiServices.builder(RecommendationRAGAgent.class)
                .chatLanguageModel(openAILanguageModel)
                .retrievalAugmentor(retrievalAugmentor)
                .build();
    }


    @Bean(name = "defaultEmbeddingStore")
    @Primary
    public EmbeddingStore<TextSegment> defaulEmbeddingStore(){
        return new InMemoryEmbeddingStore<>();
    }

    @Bean(name = "educationAndCareerEmbeddingStore")
    public EmbeddingStore<TextSegment> educationAndCareerEmbeddingStore(){
        // String filename = knowledgeBase + "EducationAndCareer.txt";
        try{
            Path filePath = getFilePath("EducationAndCareer.txt");
            return embed(filePath, embeddingModel());
        }
        catch(Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Bean(name = "entertainmentAndLifestyleEmbeddingStore")
    public EmbeddingStore<TextSegment> entertainmentAndLifestyleEmbeddingStore(){
        // String filename = knowledgeBase + "EntertainmentAndLifestyle.txt";
        try{
            Path filePath = getFilePath("EntertainmentAndLifestyle.txt");
            return embed(filePath, embeddingModel());
        }
        catch(Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Bean(name = "healthAndWellnessEmbeddingStore")
    public EmbeddingStore<TextSegment> healthAndWellnessEmbeddingStore(){
        // String filename = knowledgeBase + "HealthAndWellness.txt";
        try{
            Path filePath = getFilePath("HealthAndWellness.txt");
            return embed(filePath, embeddingModel());
        }
        catch(Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Bean(name = "humorAndMemesEmbeddingStore")
    public EmbeddingStore<TextSegment> humorAndMemesEmbeddingStore(){
        // String filename = knowledgeBase + "HumorAndMemes.txt";
        try{
            Path filePath = getFilePath("HumorAndMemes.txt");
            return embed(filePath, embeddingModel());
        }
        catch(Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Bean(name = "newsAndPoliticsEmbeddingStore")
    public EmbeddingStore<TextSegment> newsAndPoliticsEmbeddingStore(){
        // String filename = knowledgeBase + "NewsAndPolitics.txt";
        try{
            Path filePath = getFilePath("NewsAndPolitics.txt");
            return embed(filePath, embeddingModel());
        }
        catch(Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }

    @Bean(name = "technologyEmbeddingStore")
    public EmbeddingStore<TextSegment> technologyEmbeddingStore(){
        // String filename = knowledgeBase + "Technology.txt";
        try{
            Path filePath = getFilePath("Technology.txt");
            return embed(filePath, embeddingModel());
        }
        catch(Exception e){
            logger.info(e.getMessage());
        }
        return null;
    }


    private static EmbeddingStore<TextSegment> embed(Path documentPath, EmbeddingModel embeddingModel) {
        DocumentParser documentParser = new TextDocumentParser();
        Document document = loadDocument(documentPath, documentParser);

        DocumentSplitter splitter = DocumentSplitters.recursive(300, 10);
        List<TextSegment> segments = splitter.split(document);

        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();

        EmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        embeddingStore.addAll(embeddings, segments);
        return embeddingStore;
    }

    public Path getFilePath(String filename) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:KnowledgeBase/" + filename);
        return Paths.get(resource.getURI());
    }

}
