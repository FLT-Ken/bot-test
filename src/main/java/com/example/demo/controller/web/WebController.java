package com.example.demo.controller.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.vo.User;
import com.example.demo.service.UserService;
import com.google.gson.Gson;

@Controller
public class WebController {

  private final Logger logger = LoggerFactory.getLogger(WebController.class);

  private Gson gson = new Gson();

  @Autowired
  UserService userService;

  @GetMapping("/")
  public String greeting() {
    logger.info("index page");
    return "index";
  }

  @GetMapping("/page/add_user")
  public String addUserPage() {
    logger.info("addUser page");
    return "addUser";
  }

  @GetMapping("/login")
  public String login(@ModelAttribute User user) {
    logger.info("login page");
    return "login";
  }

  @PostMapping("/doLogin")
  public String doLogin(@ModelAttribute User user, Model model, HttpSession session) {
    String viewName = "login";
    try {
      user = userService.getUserByValidAccount(user.getEmail(), user.getPwd());
      if (user == null) {
        return viewName;
      }
    } catch (Exception e) {
      logger.error(e.getMessage());
      e.printStackTrace();
      return viewName;
    }
    viewName = "welcome";
    model.addAttribute("user", user);
    session.setAttribute("user", user);
    logger.info("doLogin success.");
    return viewName;
  }

  @GetMapping("/doLogout")
  public String doLogout(Model model, HttpSession session) {
    if (session.getAttribute("user") != null) {
      session.removeAttribute("user");
    }
    logger.info("doLogout success.");
    return "index";
  }
}
