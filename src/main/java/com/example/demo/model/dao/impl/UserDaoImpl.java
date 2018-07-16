package com.example.demo.model.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.dao.UserDao;
import com.example.demo.model.vo.User;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private JdbcTemplate userJdbcTemplate;

  class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new User(rs.getString("id"), rs.getString("name"), rs.getInt("age"), rs.getDate("create_time"), rs.getString("email"), rs.getString("pwd"));
    }
  }

  private static String CREATE_USER = "INSERT INTO user.user_info (id, name, age, create_time) VALUES (?,?,?,?)";
  private static String QUERY_USER = "SELECT * FROM user_info WHERE id = ?";

  @Override
  public User getOneUser(String id) {
    return userJdbcTemplate.queryForObject(QUERY_USER, new UserMapper(), new Object[] {id});
  }

  @Override
  public void addUser(User user) {
    System.out.println("EXCUTE INSERT MEMBER");
    userJdbcTemplate.update(CREATE_USER, user.getId(), user.getName(), user.getAge(), user.getCreateTime());
  }
}
