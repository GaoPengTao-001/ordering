package com.example.ordering.module;

import lombok.Data;

import java.util.List;

/**
 * @author MLH
 * @version 菜品表实体类
 */
@Data
public class Menu {
    private String id;

    private String name;

    private double price;

    private String img;

    private Integer showorder;

    List<Option> options;
}
