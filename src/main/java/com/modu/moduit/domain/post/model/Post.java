package com.modu.moduit.domain.post.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @Tsid
    private String id;

    private String userId;

    @Enumerated(EnumType.STRING)
    private PostCategory.MainCategory mainCategory;

    @Enumerated(EnumType.STRING)
    private PostCategory.SubCategory subCategory;

    private String title;

    @Lob
    private String content;

    @ElementCollection
    private List<String> hashtagIds;

    private LocalDateTime createdAt;
    private boolean isUpdated;
    private LocalDateTime updatedAt;

    private int likeCount;
    private int viewCount;
    private int commentCount;

    @Builder
    public Post(String userId, PostCategory.MainCategory mainCategory, PostCategory.SubCategory subCategory,
        String title, String content, List<String> hashtagIds) {
        this.userId = userId;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.title = title;
        this.content = content;
        this.hashtagIds = hashtagIds;
        this.createdAt = LocalDateTime.now();
        this.isUpdated = false;
        this.likeCount = 0;
        this.viewCount = 0;
        this.commentCount = 0;
    }

    public void updateTimestamp() {
        this.isUpdated = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateViewCount() {
        this.viewCount++;
    }

    public void updateLikeCount() {
        this.likeCount++;
    }

    public void updateCommentCount() {
        this.commentCount++;
    }
}
