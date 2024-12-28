package com.modu.moduit.domain.comment.model;

import com.modu.moduit.domain.user.model.Users;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {

    private String commentId;
    private String content;
    private String authorId;
    private String authorNickname;
    private LocalDateTime createdAt;
    private boolean isUpdated;
    private List<ReplyDto> replies;

    public CommentDto(String commentId, Comment comment, Users author,
        List<ReplyDto> replies) {
        this.commentId = commentId;
        this.content = comment.getContent();
        this.authorId = author.getId();
        this.authorNickname = author.getNickname();
        this.createdAt = comment.getCreatedAt();
        this.isUpdated = comment.isUpdated();
        this.replies = replies;
    }

    @Getter
    @NoArgsConstructor
    public static class ReplyDto {
        private String replyId;
        private String mentionUserId;
        private String mentionUserNickname;
        private String content;
        private String authorId;
        private String authorNickname;
        private LocalDateTime createdAt;
        private boolean isUpdated;

        public ReplyDto(String replyId, Comment comment, Users author, Users mentionUser) {
            this.replyId = replyId;
            this.mentionUserId = comment.getMentionUserId();
            this.mentionUserNickname = mentionUser.getNickname();
            this.content = comment.getContent();
            this.authorId = author.getId();
            this.authorNickname = author.getNickname();
            this.createdAt = comment.getCreatedAt();
            this.isUpdated = comment.isUpdated();
        }
    }
}
