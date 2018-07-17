package com.example.demo.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class ApiController {

  private Gson gson = new Gson();

  @PostMapping("/message/{message}")
  public String message(@PathVariable("message") String message, HttpServletRequest req) {
    System.out.println("req info: " + gson.toJson(req));
    return message;
  }
}
