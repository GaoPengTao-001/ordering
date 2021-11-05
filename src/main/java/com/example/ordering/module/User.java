package com.example.ordering.module;

import lombok.Data;

/**
 * @author MLH
 * @version 用户信息
 */
@Data
public class User {
    private String id;

    private String openId;

    private String roleId;

    private String phoneNum;
    
    private String address;
}
