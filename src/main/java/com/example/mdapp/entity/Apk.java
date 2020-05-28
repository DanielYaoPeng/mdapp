package com.example.mdapp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "apk")
public class Apk {

    @Id
    private String id;

    private String apkid;

    private String pid;

    private String apkName;

    private String apkNPY;

    private String avatar;

    private String color;

    private  String shortDescription;

    private  String description;

    private Integer status=ApkStatusEnum.Enable.getValue();

    private Boolean isTemplate;

    private Integer templateType;

    private String goodsId;

    private String libraryId;

    private Boolean isImageApk;

    private String distributeId;

    private Integer sourceType=AppSourceType.App.getValue();

    private String sourceId;

    private Boolean isNewApp= false;

    private Boolean IsMoveApp=false;

    private List<String> appIds;

    private Boolean isLock;

    private String sourceProjectId;

    private List<ExtLicence> ExtLicence;

    private String caid;

    private String uaid;

    private Date ctime;

    private Date utime;


}
