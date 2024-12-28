package com.modu.moduit.domain.post.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HashtagDto {

    private String hashtagId;
    private String hashtagName;

    @Builder
    public HashtagDto(String hashtagId, String hashtagName) {
        this.hashtagId = hashtagId;
        this.hashtagName = hashtagName;
    }
}
