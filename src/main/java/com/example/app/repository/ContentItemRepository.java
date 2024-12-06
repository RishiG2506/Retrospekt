package com.example.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.model.ContentItem;


@Repository
public interface ContentItemRepository extends JpaRepository<ContentItem, Long> {

}
