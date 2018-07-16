package com.example.demo.model.vo;

public class ServerResponseModel {

  public ServerResponseModel() {}

  public ServerResponseModel(String responseMessage) {
    this.responseMessage = responseMessage;
  }

  private String responseMessage;

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }
}
