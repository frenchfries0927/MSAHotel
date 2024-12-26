package com.playdata.front_admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinDTO {
    private Long id;
    private String name;
    private String pass;
    private String email;
    private String addr;
    private String profileImg;
    private String phone;
    private String sex;
    private Date birthDate;
    private MultipartFile image;
}
