package com.modu.moduit.domain.post.repository;

import com.modu.moduit.domain.post.model.Hashtag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<Hashtag, String> {

    Optional<Hashtag> findByName(String name);

}
