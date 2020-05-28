package com.example.mdapp.entity;

public enum AppSourceType {

    /**
     * 普通应用
     */
    App (1),

    /**
     * 商品包
     */
    MapGoods (10),

    /**
     * 应用库
     */
    AppLibrary (11),

    /**
     * 应用本地复制
     */
    AppLocal (12),
    ;

    private int intValue;

    AppSourceType(int value) {
        intValue = value;
    }

    public int getValue() {
        return intValue;
    }
}
