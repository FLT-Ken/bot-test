package com.example.demo.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.vo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.Response;

@RestController
public class ApiController {

  private final Logger logger = LoggerFactory.getLogger(ApiController.class);

  @Autowired
  UserService userService;

  @GetMapping("/user")
  public Response<List<User>> getAllUser() {
    logger.info("get all user's info.");
    return new Response<List<User>>(userService.getAllUser());
  }

  @GetMapping("/user/{id}")
  public Response<User> getUser(@PathVariable("id") String id) {
    logger.info("get " + id + "'s info.");
    if (id.equals("01")) {
      throw new IllegalArgumentException();
    }
    return new Response<User>(userService.getUser(id));
  }

  @PostMapping("/user")
  public void addUser(@ModelAttribute User user) {
    logger.info("add user");
    userService.addUser(user);
  }
}
