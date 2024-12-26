package com.playdata.front_admin.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonProperty("nickname")
    @Column(name = "nick_name", nullable = false, length = 100)
    private String nickname;

    @JsonProperty("phone")
    @Column(nullable = true, length = 20)
    private String phone;

    @JsonProperty("addr")
    @Column(nullable = false, length = 300)
    private String addr;

    @JsonProperty("profileImg")
    @Column(name = "profile_img", nullable = false, length = 1000)
    private String profileImg;  // UUID로 저장된 파일명 (경로 포함)

    @JsonProperty("birthDate")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @JsonProperty("sex")
    @Column(nullable = false, length = 20)
    private String sex;

    @JsonProperty("name")
    @Column(nullable = false, length = 50)
    private String name;

    @JsonProperty("email")
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @JsonProperty("password")
    @Column(nullable = false, length = 100)
    private String password;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // 프로필 이미지 경로 설정
    public void setProfileImage(String imagePath) {
        this.profileImg = imagePath;  // UUID 기반 경로 저장
    }

    // 프로필 이미지 경로 반환 (이미지 파일 경로를 반환)
    public String getProfileImgPath() {
        return "/images/" + this.profileImg;  // UUID 기반 경로 반환
    }
}
