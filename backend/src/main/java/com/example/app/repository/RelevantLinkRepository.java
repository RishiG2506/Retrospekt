package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.app.model.RelevantLink;
import com.example.app.model.RelevantLinkId;

import java.util.List;

@Repository
public interface RelevantLinkRepository extends JpaRepository<RelevantLink, RelevantLinkId> {

    // // Custom query to find all relevant links for a specific content item
    // List<RelevantLink> findByContentItemId(Long contentItemId);

    // // Custom query to find a relevant link by its composite key (ContentItemId + URL)
    // RelevantLink findById_ContentItemIdAndId_Url(Long contentItemId, String url);

    @Query("SELECT rl.id.url FROM RelevantLink rl WHERE rl.id.contentItemId = :contentItemId")
    List<String> findUrlsByContentItemId(@Param("contentItemId") Long contentItemId);
}
