package com.example.demo.controller.web;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

  private final Logger logger = LoggerFactory.getLogger(WebController.class);

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

  @GetMapping("/doLogout")
  public String doLogout(Model model, HttpSession session) {
    if (session.getAttribute("user") != null) {
      session.removeAttribute("user");
    }
    logger.info("doLogout success.");
    return "index";
  }
}
