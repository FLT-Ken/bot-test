package com.example.demo.service;

import java.util.List;

import com.example.demo.model.vo.User;

public interface UserService {

  List<User> getAllUser();

  User getUser(String id);

  void addUser(User user);

  User getUserByValidAccount(String email, String pwd);
}
