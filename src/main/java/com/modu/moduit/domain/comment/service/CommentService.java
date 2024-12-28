package com.modu.moduit.domain.comment.service;

import com.modu.moduit.domain.comment.model.CommentDto;
import java.util.List;

public interface CommentService {

    List<CommentDto> getCommentsByPostId(String postId);
}
