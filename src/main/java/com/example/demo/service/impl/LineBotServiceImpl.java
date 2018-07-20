package com.example.demo.service.impl;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.example.demo.model.line_bot.vo.Event;
import com.example.demo.model.line_bot.vo.LineMessage;
import com.example.demo.model.mongo.dao.ReplyRepository;
import com.example.demo.model.mongo.vo.Reply;
import com.example.demo.service.LineBotService;
import com.google.gson.Gson;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.message.StickerMessage;
import com.linecorp.bot.model.message.TextMessage;

@Service
public class LineBotServiceImpl implements LineBotService {

  @Value("${line.bot.access.token}")
  private String ACCESS_TOKEN;

  @Autowired
  Gson gson = new Gson();

  @Autowired
  ReplyRepository replyRepository;

  @Override
  public void reply(LineMessage lineMessage) {
    for (Event event : lineMessage.getEvents()) {
      switch (event.getType()) {
        case "message":
          switch (event.getMessage().getType()) {
            case "text":
              this.replyText(event);
              break;
            case "sticker":
              this.replySticker(event);
              break;
          }
          break;
      }
    }
  }

  private void replyText(Event event) {
    String recieveText = event.getMessage().getText();
    TextMessage textMessage = null;
    if (recieveText.contains("學說話;")) {
      textMessage = learn(recieveText);
    } else {
      textMessage = this.setText(recieveText);
    }
    replyToLineBot(new ReplyMessage(event.getReplyToken(), textMessage));
  }

  private TextMessage learn(String recieveText) {
    String response = recieveText;
    recieveText = recieveText.substring(recieveText.indexOf("話;") + 2);
    String keyWordAndReply[] = recieveText.split(";");
    response = "格式錯囉! 你要我學啥??";
    if (keyWordAndReply.length > 1) {
      Reply reply = new Reply(keyWordAndReply[0], keyWordAndReply[1]);
      replyRepository.save(reply);
      response = "好喔~好喔~";
    }
    return new TextMessage(response);
  }

  private void replySticker(Event event) {
    StickerMessage stickerMessage = new StickerMessage(event.getMessage().getPackageId(), event.getMessage().getStickerId());
    replyToLineBot(new ReplyMessage(event.getReplyToken(), stickerMessage));
  }

  private TextMessage setText(String recieveText) {
    String replyText = recieveText;
    Reply reply = replyRepository.findByKeyWord(recieveText);
    if (reply != null) {
      replyText = reply.getReply();
    } else if (recieveText.contains("因該")) {
      replyText = replyRepository.findByKeyWord("因該").getReply();
    } else {
      replyText = recieveText;
    }
    return new TextMessage(replyText);
  }

  private void replyToLineBot(ReplyMessage replyMessage) {
    LineMessagingClient client = LineMessagingClient.builder(ACCESS_TOKEN).build();
    try {
      client.replyMessage(replyMessage).get();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
}
