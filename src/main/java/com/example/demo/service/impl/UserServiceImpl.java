package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.dao.UserDao;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.model.vo.User;
import com.example.demo.service.UserService;
import com.google.gson.Gson;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDao userDao;

  @Autowired
  UserRepository userRepository;

  private Gson gson = new Gson();

  @Override
  public User getUser(String id) {
    // return userDao.getOneUser(id);
    Optional<User> result = userRepository.findById(id);
    System.out.println("result: " + gson.toJson(result));
    return result.isPresent() ? result.get() : null;
  }

  @Override
  public void addUser(User user) {
    userDao.addUser(user);
  }

  @Override
  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  @Override
  public User getUserByValidAccount(String email, String pwd) {
    Optional<User> result = userRepository.findByEmailAndPwd(email, pwd);
    return result.isPresent() ? result.get() : null;
  }
}
