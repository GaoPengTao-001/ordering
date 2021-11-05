package com.example.ordering.util;

import java.util.UUID;

/**
 * @author MLH
 * @version 生成主键
 */
public class KeyUtil {

    public static String uuId() {
        return UUID.randomUUID().toString().replaceAll("-", "");

    }
}
