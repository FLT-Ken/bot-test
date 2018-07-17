package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.line_bot.vo.Line;
import com.google.gson.Gson;

@RestController
public class ApiController {

  private Gson gson = new Gson();

  @PostMapping("/reply")
  public String message(@RequestBody Object body) {
    System.out.println("origin body: " + gson.toJson(body));
    Line line = (Line) body;
    System.out.println(gson.toJson(line));
    return line.getEvents().get(0).getMessage().getText();
  }
}
