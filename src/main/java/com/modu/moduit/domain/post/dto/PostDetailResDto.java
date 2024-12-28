package com.modu.moduit.domain.post.dto;

import com.modu.moduit.domain.comment.model.CommentDto;
import com.modu.moduit.domain.post.model.HashtagDto;
import com.modu.moduit.domain.post.model.Post;
import com.modu.moduit.domain.user.model.Users;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDetailResDto {

    private String postId;
    private String title;
    private String content;
    private String mainCategory;
    private String subCategory;
    private String userNickname;
    private String userId;

    private LocalDateTime createdAt;
    private boolean isUpdated;
    private LocalDateTime updatedAt;

    private int likeCount;
    private int commentCount;
    private int viewCount;

    private List<HashtagDto> hashtags;
    private List<CommentDto> comments;

    @Builder
    public PostDetailResDto(String postId, Post post, String authorId, Users author,
        List<HashtagDto> hashtags, List<CommentDto> comments) {
        this.postId = postId;
        this.title = post.getTitle();
        this.content = post.getContent();
        this.mainCategory = String.valueOf(post.getMainCategory());
        this.subCategory = String.valueOf(post.getSubCategory());
        this.userNickname = author.getNickname();
        this.userId = authorId;
        this.createdAt = post.getCreatedAt();
        this.isUpdated = post.isUpdated();
        this.updatedAt = post.getUpdatedAt();
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentCount();
        this.viewCount = post.getViewCount();
        this.hashtags = hashtags;
        this.comments = comments;
    }
}
