package com.example.demo.model.mongo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.mongo.vo.Reply;

public interface ReplyRepository extends MongoRepository<Reply, String> {

  List<Reply> findByKeyWord(String keyWord);
}
