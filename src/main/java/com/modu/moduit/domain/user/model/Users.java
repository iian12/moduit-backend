package com.modu.moduit.domain.user.model;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users {

    @Id
    @Tsid
    private String id;

    private String email;
    private String password;
    private String nickname;

    private String profileImgUrl;
    private String description;

    @Enumerated(EnumType.STRING)
    private JobRoles.MainCategory mainCategory;

    @Enumerated(EnumType.STRING)
    private JobRoles.SubCategory subCategory;

    private int likeCount;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Provider> providers;

    @ElementCollection
    private List<String> subjectIds;

    @Builder
    public Users(String email, String password, String nickname, String profileImgUrl,
        String description, JobRoles.MainCategory mainCategory, JobRoles.SubCategory subCategory,
        Role role, List<Provider> providers, List<String> subjectIds) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.profileImgUrl = profileImgUrl;
        this.description = description;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.likeCount = 0;
        this.role = role;
        this.providers = providers != null ? providers : new ArrayList<>();
        this.subjectIds = subjectIds != null ? subjectIds : new ArrayList<>();
    }

    public void addProvider(Provider oauthProvider, String subjectId) {
        this.providers.add(oauthProvider);
        this.subjectIds.add(subjectId);
    }
}
