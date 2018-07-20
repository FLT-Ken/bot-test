package com.example.demo.model.mongo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.mongo.vo.Reply;

public interface ReplyRepository extends MongoRepository<Reply, String> {

  Reply findByKeyWord(String keyWord);
}
