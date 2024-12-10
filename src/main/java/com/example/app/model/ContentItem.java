package com.example.app.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "content_items")
public class ContentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "author")
    private String author;

    @Lob
    @Column(name = "summary")
    private String summary;

    @Column(name = "sent")
    private boolean sent;

    @OneToMany(mappedBy = "contentItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RelevantLink> relevantLinks = new HashSet<>();

    // Constructors
    public ContentItem() {
    }

    public ContentItem(String url, String category, String author, String summary) {
        this.url = url;
        this.category = category;
        this.author = author;
        this.summary = summary;
        this.sent = false;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setEmailSent(){
        this.sent = true;
    }

    public Set<RelevantLink> getRelevantLinks() {
        return relevantLinks;
    }

    public void setRelevantLinks(Set<RelevantLink> relevantLinks) {
        this.relevantLinks = relevantLinks;
    }

    public void addRelevantLink(RelevantLink relevantLink) {
        relevantLinks.add(relevantLink);
        // relevantLink.setContentItem(this);
    }

    public void removeRelevantLink(RelevantLink relevantLink) {
        relevantLinks.remove(relevantLink);
        relevantLink.setContentItem(null);
    }

    // toString (optional)
    @Override
    public String toString() {
        return "ContentItem{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", category='" + category + '\'' +
                ", author='" + author + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
