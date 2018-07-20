package com.example.demo.model.mongo.vo;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reply_message")
public class Reply {

  public Reply() {}

  public Reply(String keyWord, String reply) {
    Date now = new Date();
    this.keyWord = keyWord;
    this.reply = reply;
    this.createdTime = now;
    this.modifiedTime = now;
  }

  private String keyWord;

  private String reply;

  private Date createdTime;

  private Date modifiedTime;

  public String getKeyWord() {
    return keyWord;
  }

  public void setKeyWord(String keyWord) {
    this.keyWord = keyWord;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(Date modifiedTime) {
    this.modifiedTime = modifiedTime;
  }
}
