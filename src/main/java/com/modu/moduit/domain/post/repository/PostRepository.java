package com.modu.moduit.domain.post.repository;

import com.modu.moduit.domain.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {

}
