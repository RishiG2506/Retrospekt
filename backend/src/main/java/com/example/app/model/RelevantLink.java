package com.example.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "relevant_links")
public class RelevantLink {

    @EmbeddedId
    private RelevantLinkId id;  // Using composite key

    @ManyToOne
    @MapsId("contentItemId")
    @JsonIgnore
    private ContentItem contentItem;  // Many-to-one relationship


    // Default constructor
    public RelevantLink() {}

    // Constructor with fields
    public RelevantLink(RelevantLinkId id, ContentItem contentItem) {
        this.id = id;
        this.contentItem = contentItem;
    }

    // Getters and Setters
    public RelevantLinkId getId() {
        return id;
    }

    public void setId(RelevantLinkId id) {
        this.id = id;
    }

    public ContentItem getContentItem() {
        return contentItem;
    }

    public void setContentItem(ContentItem contentItem) {
        this.contentItem = contentItem;
    }
}
