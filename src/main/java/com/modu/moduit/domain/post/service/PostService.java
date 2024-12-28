package com.modu.moduit.domain.post.service;

import com.modu.moduit.domain.post.dto.CreatePostReqDto;
import com.modu.moduit.domain.post.dto.PostDetailResDto;

public interface PostService {

    String createPost(CreatePostReqDto reqDto, String token);

    PostDetailResDto getPostDetail(String postId);
}
