package com.example.mdapp.entity;

public enum ApkStatusEnum {

    /**
     * 关闭
     */
    Unable(0),

    /**
     * 开启
     */
    Enable(1),

    /**
     * 删除
     */
    IsDeleted(2),
    ;

    private int intValue;

    ApkStatusEnum(int value) {
        intValue = value;
    }

    public int getValue() {
        return intValue;
    }
}
