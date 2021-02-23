package com.example.mdapp.entity;

public enum AppType {

    /**
     * 关闭
     */
    Worksheet(3),

    /**
     * 开启
     */
    Kc(4),

    /**
     * 删除
     */
    Task(5),
    ;

    private int intValue;

    AppType(int value) {
        intValue = value;
    }

    public int getValue() {
        return intValue;
    }

}
