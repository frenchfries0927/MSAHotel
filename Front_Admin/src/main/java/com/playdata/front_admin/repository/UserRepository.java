package com.playdata.front_admin.repository;

import com.playdata.front_admin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// JpaRePository 를 상속 받으면 findAll 메소드가 지원 된다
public interface UserRepository extends JpaRepository<User, Long> {

}
