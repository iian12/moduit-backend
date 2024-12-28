package com.modu.moduit.domain.comment.service;

import com.modu.moduit.domain.comment.model.Comment;
import com.modu.moduit.domain.comment.model.CommentDto;
import com.modu.moduit.domain.comment.repository.CommentRepository;
import com.modu.moduit.domain.user.model.Users;
import com.modu.moduit.domain.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {


    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
            .map(comment -> {
                Users author = userRepository.findById(comment.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

                List<Comment> replies = commentRepository.findByParentCommentId(comment.getId());

                List<CommentDto.ReplyDto> replyDtos = replies.stream()
                    .map(reply -> {
                        Users replyAuthor = userRepository.findById(reply.getUserId())
                            .orElseThrow(() -> new RuntimeException("User not found"));
                        Users mentionUser = userRepository.findById(reply.getMentionUserId())
                            .orElseThrow(() -> new RuntimeException("User not found"));
                        return new CommentDto.ReplyDto(reply.getId(), reply, replyAuthor, mentionUser);
                    })
                    .toList();

                return new CommentDto(comment.getId(), comment, author, replyDtos);
            })
            .collect(Collectors.toList());
    }
}
