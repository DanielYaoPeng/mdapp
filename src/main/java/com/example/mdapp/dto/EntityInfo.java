package com.example.mdapp.dto;

import lombok.Data;

@Data
public class EntityInfo {
    public String id ;

    public String name ;

    /// <summary>
    /// 类型，0=工作表 1=自定义页面
    /// </summary>
    public Integer type = AppEntityType.Worksheet.getValue();
    /// <summary>
    /// 工作表显隐状态  1=正常 2=关闭
    /// </summary>
    public int Status = 1;

    public String icon ;

    public String iconColor;

    public String iconUrl;

    public EntityInfo(String id,String name,Integer type, String icon,String iconColor){
        this.id=id;
        this.name=name;
        this.type=type;
        this.icon=icon;
        this.iconColor=iconColor;

    }
}
