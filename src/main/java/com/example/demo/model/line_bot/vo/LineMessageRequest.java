package com.example.demo.model.line_bot.vo;

import java.util.List;

public class LineMessageRequest {

  public LineMessageRequest() {}

  public LineMessageRequest(String replyToken, List<Message> messages) {
    this.replyToken = replyToken;
    this.messages = messages;
  }

  private String replyToken;

  private List<Message> messages;

  public String getReplyToken() {
    return replyToken;
  }

  public void setReplyToken(String replyToken) {
    this.replyToken = replyToken;
  }

  public List<Message> getMessages() {
    return messages;
  }

  public void setMessages(List<Message> messages) {
    this.messages = messages;
  }
}
