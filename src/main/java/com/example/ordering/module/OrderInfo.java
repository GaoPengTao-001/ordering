package com.example.ordering.module;

import lombok.Data;

import java.util.List;

/**
 * @author MLH
 * @version 订单详情
 */
@Data
public class OrderInfo {
    private List<OrderMenu> orderMenus;

    private User userInfo;
}
