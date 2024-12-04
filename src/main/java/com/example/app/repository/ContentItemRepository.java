package com.example.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.ContentItem;

import java.util.Optional;

@Repository
public interface ContentItemRepository extends JpaRepository<ContentItem, Long> {

    // Custom query to find a content item by URL
    Optional<ContentItem> findByUrl(String url);
}
