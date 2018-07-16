package com.example.demo.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.vo.User;

public interface UserRepository extends JpaRepository<User, String> {

  List<User> findAll();

  Optional<User> findById(String id);

  Optional<User> findByEmailAndPwd(String email, String pwd);
}
