package com.example.demo.model.dao;

import com.example.demo.model.vo.User;

public interface UserDao {

  User getOneUser(String id);

  void addUser(User user);
}
