package com.example.ordering.controller;

import com.example.ordering.module.Menu;
import com.example.ordering.module.OrderInfo;
import com.example.ordering.module.User;
import com.example.ordering.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author MLH
 * @version 处理用户端请求
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/queryMenu")
    public List<Menu> queryMenu() {
        return menuService.queryMenu();
    }

    @RequestMapping("/queryUser")
    public User queryUser() {
        return menuService.queryUser();
    }

    @RequestMapping("/saveOrderMenu")
    public void saveOrderMenu(@RequestBody OrderInfo orderInfo) {
        menuService.saveOrderMenu(orderInfo);
    }

    @RequestMapping("/getOpenId")
    public void getOpenId(@RequestBody String code) {
        menuService.getOpenId(code);
    }

}
