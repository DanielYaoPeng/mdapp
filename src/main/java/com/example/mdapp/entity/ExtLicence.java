package com.example.mdapp.entity;

import java.util.Date;

import lombok.Data;
@Data
public class ExtLicence {

    private String licenceId;

    /**
     * 1 =免费 2=销售 3=试用
     */
    private Integer type;

    private Boolean isStop;

    private Date startTime;

    private Date endTime;
}
