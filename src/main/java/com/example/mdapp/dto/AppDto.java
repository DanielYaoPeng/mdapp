package com.example.mdapp.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AppDto {
    public String appId;

    public Boolean isLock;
    public String goodsId;
    public String distributeId;
    public String sourceProjectId;
    public Boolean isGoodsStatus;
    public Boolean isImageApk;
    public String projectId;
    public String appName;
    public String appNamePinyin;
    public String icon ;
    public String color;
    public String description ;
    public Integer apkStatus;
    public String createAccountId;
    public UserInfos createAccountInfo;
    public Date ceateTime;
    public Date updateTime;
    public Integer permissionType;
    public List<LicenceModel> licence;
    public List<String> appSectionIds;
    public List<EntityInfo> infos;
    public List<String> worksheetIds;
    public String iconUrl;

    
    
}
