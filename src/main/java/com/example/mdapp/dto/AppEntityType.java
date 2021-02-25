package com.example.mdapp.dto;

public enum AppEntityType {
     /**
     * 工作表
     */
    Worksheet(0),

    /**
     * 自定义页面
     */
    CustomPageKc(1),

    /**
     * 子表
     */
    ChildWorksheet(10),
    ;

    private int intValue;

    AppEntityType(int value) {
        intValue = value;
    }

    public int getValue() {
        return intValue;
    }
}
