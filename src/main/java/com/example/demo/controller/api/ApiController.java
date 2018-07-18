package com.example.demo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.line_bot.vo.LineMessage;
import com.example.demo.service.LineBotService;
import com.google.gson.Gson;

@RestController
public class ApiController {

  private Gson gson = new Gson();

  @Autowired
  LineBotService lineBotService;

  @PostMapping("/reply")
  public void message(@RequestBody LineMessage lineMsg) {
    System.out.println("request body: " + gson.toJson(lineMsg));
    lineBotService.reply(lineMsg);
  }
}
