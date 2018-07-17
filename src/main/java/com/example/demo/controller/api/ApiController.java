package com.example.demo.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.line_bot.vo.LineMessage;
import com.google.gson.Gson;

@RestController
public class ApiController {

  private Gson gson = new Gson();

  @PostMapping("/reply")
  public String message(@RequestBody LineMessage lineMsg) {
    System.out.println("request body: " + gson.toJson(lineMsg));
    return lineMsg.getEvents().get(0).getMessage().getText();
  }
}
