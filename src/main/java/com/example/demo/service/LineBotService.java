package com.example.demo.service;

import com.example.demo.model.line_bot.vo.LineMessage;

public interface LineBotService {

  void reply(LineMessage lineMessage);
}
