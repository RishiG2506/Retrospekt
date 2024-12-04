package com.example.app.repository;

import com.example.app.model.ContentItemRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TweetRepository extends JpaRepository<ContentItemRequest, Long> {
}
