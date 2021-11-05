package com.example.ordering.dao;

import com.example.ordering.module.Menu;
import com.example.ordering.module.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {

    List<Menu> selectMenu();

    User selectUser(@Param("openId") String openId);
    
    void insertUser(@Param("user") User user);
    
    void updateUser(@Param("user") User user);
}
