package com.modu.moduit.domain.comment.repository;

import com.modu.moduit.domain.comment.model.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findByPostId(String postId);

    List<Comment> findByParentCommentId(String parentCommentId);
}
