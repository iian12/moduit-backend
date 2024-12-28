package com.modu.moduit.domain.post.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostReqDto {

    private String title;
    private String content;
    private List<String> hashtagNames;
    private String mainCategory;
    private String subCategory;

    @Builder
    public CreatePostReqDto(String title, String content, List<String> hashtagNames, String mainCategory, String subCategory) {
        this.title = title;
        this.content = content;
        this.hashtagNames = hashtagNames;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }
}
