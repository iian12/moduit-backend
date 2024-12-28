package com.modu.moduit.domain.post.model;

import java.util.Arrays;
import lombok.Getter;

public class PostCategory {

    @Getter
    public enum MainCategory {
        QNA,
        KNOWLEDGE,
        COMMUNITY;

        public static MainCategory fromString(String value) {
            return Arrays.stream(MainCategory.values())
                .filter(category -> category.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid MainCategory: " + value));
        }
    }

    @Getter
    public enum SubCategory {
        PROGRAMMING_QNA(MainCategory.QNA),
        DESIGN_QNA(MainCategory.QNA),
        PLANNING_QNA(MainCategory.QNA),
        MARKETING_QNA(MainCategory.QNA),
        OTHER_QNA(MainCategory.QNA),
        CAREER_QNA(MainCategory.QNA),

        TECH_ARTICLES(MainCategory.KNOWLEDGE),
        PRACTICAL_GUIDES(MainCategory.KNOWLEDGE),
        CASE_STUDIES(MainCategory.KNOWLEDGE),

        DAILY_LIFE(MainCategory.COMMUNITY),
        DISCUSSION(MainCategory.COMMUNITY),
        STUDY_AND_PROJECTS(MainCategory.COMMUNITY);

        private final MainCategory mainCategory;

        SubCategory(MainCategory mainCategory) {
            this.mainCategory = mainCategory;
        }

        public static SubCategory fromString(String value) {
            return Arrays.stream(SubCategory.values())
                .filter(category -> category.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid SubCategory: " + value));
        }
    }
}