package com.example.ordering.module;

import lombok.Data;

/**
 * @author MLH
 * @version 获取openid信息的实体类
 */
@Data
public class OpenResust {
    private String access_token;
    private String expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
}
