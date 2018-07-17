package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.line_bot.vo.Line;
import com.google.gson.Gson;

@RestController
public class ApiController {

  private Gson gson = new Gson();

  @PostMapping("/message/{message}")
  public String message(@PathVariable("message") String message, @RequestBody Line line) {
    System.out.println(gson.toJson(line));
    return message;
  }
}
