package com.modu.moduit.domain.post.service;

import com.modu.moduit.domain.comment.model.CommentDto;
import com.modu.moduit.domain.comment.service.CommentService;
import com.modu.moduit.domain.post.dto.CreatePostReqDto;
import com.modu.moduit.domain.post.dto.PostDetailResDto;
import com.modu.moduit.domain.post.model.Hashtag;
import com.modu.moduit.domain.post.model.HashtagDto;
import com.modu.moduit.domain.post.model.Post;
import com.modu.moduit.domain.post.model.PostCategory;
import com.modu.moduit.domain.post.repository.HashtagRepository;
import com.modu.moduit.domain.post.repository.PostRepository;
import com.modu.moduit.domain.user.model.Users;
import com.modu.moduit.domain.user.repository.UserRepository;
import com.modu.moduit.global.security.jwt.TokenUtils;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final HashtagRepository hashtagRepository;
    private final CommentService commentService;

    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository,
        HashtagRepository hashtagRepository,
        CommentService commentService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.hashtagRepository = hashtagRepository;
        this.commentService = commentService;
    }

    @Override
    public String createPost(CreatePostReqDto reqDto, String token) {
        Users user = userRepository.findById(TokenUtils.getUserIdFromToken(token))
            .orElseThrow(() -> new IllegalArgumentException("Invalid User"));

        PostCategory.MainCategory mainCategory = PostCategory.MainCategory.fromString(
            reqDto.getMainCategory());
        PostCategory.SubCategory subCategory = PostCategory.SubCategory.fromString(
            reqDto.getSubCategory());

        if (!mainCategory.equals(subCategory.getMainCategory())) {
            throw new IllegalArgumentException("Invalid category combination");
        }

        if (reqDto.getHashtagNames().size() > 5) {
            throw new IllegalArgumentException("Hashtag name too long");
        }

        List<String> hashtagIds = new ArrayList<>();

        for (String hashtagName : reqDto.getHashtagNames()) {
            Hashtag hashtag = hashtagRepository.findByName(hashtagName)
                .orElseGet(() -> {
                    Hashtag newHashtag = Hashtag.builder()
                        .name(hashtagName)
                        .build();
                    return hashtagRepository.save(newHashtag);
                });

            hashtagIds.add(hashtag.getId());
        }

        Post newPost = Post.builder()
            .title(reqDto.getTitle())
            .content(reqDto.getContent())
            .userId(user.getId())
            .mainCategory(mainCategory)
            .subCategory(subCategory)
            .hashtagIds(hashtagIds)
            .build();

        postRepository.save(newPost);

        return newPost.getId();
    }

    @Override
    public PostDetailResDto getPostDetail(String postId) {
        Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("Invalid Post ID"));

        post.updateViewCount();

        Users author = userRepository.findById(post.getUserId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid User"));

        List<HashtagDto> hashtagDtos = hashtagRepository.findAllById(post.getHashtagIds())
            .stream()
            .map(hashtag -> HashtagDto.builder()
                .hashtagId(hashtag.getId())
                .hashtagName(hashtag.getName())
                .build())
            .toList();

        List<CommentDto> commentDtos = commentService.getCommentsByPostId(post.getId());

        return PostDetailResDto.builder()
            .postId(post.getId())
            .post(post)
            .authorId(author.getId())
            .author(author)
            .hashtags(hashtagDtos)
            .comments(commentDtos)
            .build();
    }
}
