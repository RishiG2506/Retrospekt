package com.example.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelevantLinkId implements Serializable {

    @Column(name = "content_item_id")
    private Long contentItemId;

    @Column(name = "relevant_url")
    private String url;

    // Default constructor
    public RelevantLinkId() {}

    public RelevantLinkId(Long contentItemId, String url) {
        this.contentItemId = contentItemId;
        this.url = url;
    }

    public Long getContentItemId() {
        return contentItemId;
    }

    public void setContentItemId(Long contentItemId) {
        this.contentItemId = contentItemId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelevantLinkId that = (RelevantLinkId) o;
        return Objects.equals(contentItemId, that.contentItemId) &&
               Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentItemId, url);
    }
}
