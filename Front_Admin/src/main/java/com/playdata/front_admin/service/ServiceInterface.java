package com.playdata.front_admin.service;

import com.playdata.front_admin.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ServiceInterface {

    // Pageable을 사용한 페이징 처리
    Page<User> userPage(Pageable pageable);

    // 사용자 ID로 검색
    Optional<User> findById(Long id);
    void deleteById(Long id);

    // 사용자 저장
    User save(User user);

}
