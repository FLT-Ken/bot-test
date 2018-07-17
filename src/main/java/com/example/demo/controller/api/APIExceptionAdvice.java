package com.example.demo.controller.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.util.Response;

@ControllerAdvice(basePackages = {"com.example.demo.controller.api"})
public class APIExceptionAdvice {

  private static final Logger logger = LoggerFactory.getLogger(APIExceptionAdvice.class);

  @ExceptionHandler(value = {IllegalArgumentException.class, HttpMessageNotReadableException.class, MissingServletRequestParameterException.class,
      ServletRequestBindingException.class})
  public ResponseEntity<Response<?>> IllegalArgumentExceptionHandler(Exception e, WebRequest request) {
    logger.info(e.getMessage(), e);
    return new ResponseEntity<Response<?>>(new Response<>("400.001", "failure", "參數錯誤"), HttpStatus.OK);
  }

  // 僅處理RuntimeException
  @ExceptionHandler(value = {RuntimeException.class})
  public ResponseEntity<Response<?>> runTimeExceptionHandler(RuntimeException re, WebRequest request) {
    logger.error(re.getMessage(), re);
    return new ResponseEntity<Response<?>>(new Response<>("500", "failure", re.getMessage()), HttpStatus.OK);
  }

  // 處理Exception
  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Response<?>> exceptionHandler(Exception e, WebRequest request) {
    logger.error(e.getMessage(), e);
    return new ResponseEntity<Response<?>>(new Response<>("500", "failure", e.getMessage()), HttpStatus.OK);
  }
}
