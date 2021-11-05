package com.example.ordering.service;

import com.alibaba.fastjson.JSON;
import com.example.ordering.dao.MenuMapper;
import com.example.ordering.module.Menu;
import com.example.ordering.module.OpenResust;
import com.example.ordering.module.OrderInfo;
import com.example.ordering.module.User;
import com.example.ordering.module.WeiXinResult;
import com.example.ordering.util.KeyUtil;
import com.example.ordering.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author MLH
 * @version 菜品服务
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Resource
    private RestTemplate restTemplate;

    @Value("${weixin.appID}")
    private String appId;

    @Value("${weixin.appsecret}")
    private String appsecret;

    @Value("${weixin.redirect}")
    private String redirect;

    @Value("${weixin.openIdUrl}")
    private String openIdUrl;


    public List<Menu> queryMenu() {
        return menuMapper.selectMenu();
    }

    public User queryUser() {
        String openId = SessionUtil.getObject("openId", String.class);
        if (!StringUtils.isEmpty(openId)) {
            return menuMapper.selectUser(openId);
        }
        return null;
    }

    public void getOpenId(String code) {
        ResponseEntity<String> result = restTemplate.getForEntity(String.format(openIdUrl, appId, appsecret, code), String.class);
        if (HttpStatus.OK == result.getStatusCode()) {
            OpenResust openResust = JSON.parseObject(result.getBody(), OpenResust.class);
            if (null != openResust && !StringUtils.isEmpty(openResust.getOpenid())) {
                String openId = openResust.getOpenid();
                SessionUtil.saveObject("openId", openResust.getOpenid());
                User user = menuMapper.selectUser(openId);
                if (null == user) {
                    saveUser(openId);
                }
            }
        }
    }

    public void saveOrderMenu(OrderInfo info) {
        String openId = SessionUtil.getObject("openId", String.class);
        if (StringUtils.isEmpty(openId)) {
            return;
        }
        User user = menuMapper.selectUser(openId);
        if (null == user) {
            return;
        }
        User orderUser = info.getUserInfo();
        user.setPhoneNum(orderUser.getPhoneNum());
        user.setAddress(orderUser.getAddress());
        menuMapper.updateUser(user);
        // 保存订单
        

    }

    private void saveUser(String openId) {
        User user = new User();
        user.setId(KeyUtil.uuId());
        user.setOpenId(openId);
        menuMapper.insertUser(user);
    }
}
