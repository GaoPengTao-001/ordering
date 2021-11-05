package com.example.ordering.module;

import lombok.Data;

/**
 * @author MLH
 * @version 菜品规格
 */
@Data
public class Option {
    private String id;

    private String menuId;

    private String name;

    private double price;

    private Integer showorder;
}
