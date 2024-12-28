package com.modu.moduit.domain.comment.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @Tsid
    private String id;

    private String userId;
    private String postId;

    private String content;

    private int likeCount;

    private LocalDateTime createdAt;
    private boolean isUpdated;

    private String parentCommentId;
    private String mentionUserId;

    @Builder
    public Comment(String userId, String postId, String content, int likeCount, String parentCommentId,
        String mentionUserId) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = LocalDateTime.now();
        this.isUpdated = false;
        this.parentCommentId = parentCommentId;
        this.mentionUserId = mentionUserId;
    }

    public void updateComment(String newContent) {
        this.content = newContent;
        this.isUpdated = true;
    }
}
