package com.example.demo.util;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

  @JsonInclude(JsonInclude.Include.USE_DEFAULTS)
  private T result;

  private String code;

  private String status;

  private String message;

  public Response() {}

  public Response(T result) {
    if (StringUtils.isEmpty(result)) {
      this.code = "404";
    } else {
      this.code = "200";
    }
    this.result = result;
    this.status = "success";
  }

  public Response(T result, String code, String status) {
    this.result = result;
    this.code = code;
    this.status = status;
  }

  public Response(String code, String status, String message) {
    this.code = code;
    this.status = status;
    this.message = message;
  }

  public Response(T result, String code, String status, String message) {
    this.result = result;
    this.code = code;
    this.status = status;
    this.message = message;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
