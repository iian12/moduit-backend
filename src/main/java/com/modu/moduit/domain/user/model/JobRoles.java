package com.modu.moduit.domain.user.model;

import lombok.Getter;

public class JobRoles {

    @Getter
    public enum MainCategory {
        DEVELOPMENT("개발"),
        PLANNING("기획"),
        DESIGN("디자인"),
        MARKETING("마케팅"),
        ETC("기타");

        private final String displayName;

        MainCategory(String displayName) {
            this.displayName = displayName;
        }

    }

    @Getter
    public enum SubCategory {
        BACKEND("백엔드 개발", MainCategory.DEVELOPMENT),
        FRONTEND("프론트엔드 개발", MainCategory.DEVELOPMENT),
        MOBILE("모바일 개발", MainCategory.DEVELOPMENT),
        FULLSTACK("풀스택 개발", MainCategory.DEVELOPMENT),
        EMBEDDED("임베디드 개발", MainCategory.DEVELOPMENT),
        WINDOWS_APP("윈도우 어플리케이션 개발", MainCategory.DEVELOPMENT),
        DBA("DBA", MainCategory.DEVELOPMENT),
        PUBLISHER("퍼블리셔", MainCategory.DEVELOPMENT),
        QA("QA", MainCategory.DEVELOPMENT),
        OTHER_DEVELOPMENT("기타 개발", MainCategory.DEVELOPMENT),

        STRATEGY("전략 기획", MainCategory.PLANNING),
        SERVICE("서비스 기획", MainCategory.PLANNING),
        UI_UX("UI/UX 기획", MainCategory.PLANNING),
        BUSINESS("사업 기획", MainCategory.PLANNING),
        PM("PM", MainCategory.PLANNING),
        OTHER_PLANNING("기타 기획", MainCategory.PLANNING),

        WEB_DESIGN("웹 디자인", MainCategory.DESIGN),
        UI_DESIGN("UI 디자인", MainCategory.DESIGN),
        UX_DESIGN("UX 디자인", MainCategory.DESIGN),
        MOBILE_DESIGN("모바일 디자인", MainCategory.DESIGN),
        SERVICE_DESIGN("서비스 디자인", MainCategory.DESIGN),
        OTHER_DESIGN("기타 디자인", MainCategory.DESIGN),

        SALES("영업", MainCategory.MARKETING),
        MARKETING("마케팅", MainCategory.MARKETING),
        STRATEGY_LEAD("전략 수석", MainCategory.MARKETING),
        MD("MD", MainCategory.MARKETING),
        OTHER_MARKETING("기타 마케팅", MainCategory.MARKETING),

        OTHER("기타", MainCategory.ETC);

        private final String displayName;
        private final MainCategory mainCategory;

        SubCategory(String displayName, MainCategory mainCategory) {
            this.displayName = displayName;
            this.mainCategory = mainCategory;
        }

    }
}
