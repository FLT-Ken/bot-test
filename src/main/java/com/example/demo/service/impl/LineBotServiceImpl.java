package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.line_bot.vo.Event;
import com.example.demo.model.line_bot.vo.LineMessage;
import com.example.demo.model.line_bot.vo.Message;
import com.example.demo.service.LineBotService;

public class LineBotServiceImpl implements LineBotService {

  @Value("${line.bot.access.token}")
  private String ACCESS_TOKEN;

  @Override
  public void reply(LineMessage lineMessage) {
    for (Event event : lineMessage.getEvents()) {
      switch (event.getType()) {
        case "message":
          switch (event.getMessage().getType()) {
            case "text":
              this.sendResponseMessages(event.getReplyToken(), event.getMessage().getText());
              break;
          }
          break;
      }
    }
  }

  private void sendResponseMessages(String replyToken, String text) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    headers.add("Authorization", "Bearer " + ACCESS_TOKEN);

    Event event = new Event(replyToken, new Message("text", this.getText(text)));

    HttpEntity<Event> entity = new HttpEntity<Event>(event, headers);

    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> lineResponse = restTemplate.exchange("https://api.line.me/v2/bot/message/reply", HttpMethod.POST, entity, String.class);
    if (lineResponse.getStatusCode() == HttpStatus.OK) {
      System.out.println("seccess");
    } else {
      System.out.println("fail");
    }
  }

  private String getText(String originText) {
    String reply = originText;
    if (originText.contains("安安")) {
      reply = "尼好~";
    } else if (originText.contains("因該")) {
      reply = "應啦幹";
    }
    return reply;
  }
}
