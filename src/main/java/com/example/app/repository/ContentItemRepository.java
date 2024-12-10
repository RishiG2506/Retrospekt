package com.example.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.app.model.ContentItem;


@Repository
public interface ContentItemRepository extends JpaRepository<ContentItem, Long> {
    List<ContentItem> findByCategory(String category);

    @Query("SELECT c FROM ContentItem c WHERE c.sent = false")
    List<ContentItem> findUnsentContent();
}
