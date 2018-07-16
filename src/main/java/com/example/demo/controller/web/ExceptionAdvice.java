package com.example.demo.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = {"com.example.demo.controller.web"})
public class ExceptionAdvice {

  private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

  @ExceptionHandler(Exception.class)
  public ModelAndView exceptionHandler(Exception e) {
    logger.error("error msg:" + e.getMessage(), e);
    return new ModelAndView("error");
  }
}
