package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  @PostMapping("/message/{message}")
  public String message(@PathVariable("message") String message) {
    return message;
  }
}
