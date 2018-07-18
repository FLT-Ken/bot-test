package com.example.demo.service.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.demo.model.line_bot.vo.Event;
import com.example.demo.model.line_bot.vo.LineMessage;
import com.example.demo.service.LineBotService;
import com.google.gson.Gson;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.TextMessage;

@Service
public class LineBotServiceImpl implements LineBotService {

  @Value("${line.bot.access.token}")
  private String ACCESS_TOKEN;

  private Gson gson = new Gson();

  @Override
  public void reply(LineMessage lineMessage) {
//    for (Event event : lineMessage.getEvents()) {
//      switch (event.getType()) {
//        case "message":
//          switch (event.getMessage().getType()) {
//            case "text":
//              this.sendResponseMessages(event.getReplyToken(), event.getMessage().getText());
//              break;
//          }
//          break;
//      }
//    }
  }

  private void sendResponseMessages(String replyToken, String text) {
    System.out.println("ACCESS_TOKEN: " + ACCESS_TOKEN);
    LineMessagingClient client = LineMessagingClient.builder(ACCESS_TOKEN).build();
    ReplyMessage replyMessage = new ReplyMessage(replyToken, this.getText(text));
    System.out.println("replyMessage: " + gson.toJson(replyMessage));
    try {
      client.replyMessage(replyMessage).get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
      return;
    }
  }

  private TextMessage getText(String originText) {
    String reply = originText;
    if (originText.contains("安安")) {
      reply = "尼好~";
    } else if (originText.contains("因該")) {
      reply = "應啦幹";
    }
    return new TextMessage(reply);
  }
}
