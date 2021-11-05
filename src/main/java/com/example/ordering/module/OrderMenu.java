package com.example.ordering.module;

import lombok.Data;

/**
 * @author MLH
 * @version 订单里的菜单
 */
@Data
public class OrderMenu {
    private Menu menu;

    private Option opt;

}
