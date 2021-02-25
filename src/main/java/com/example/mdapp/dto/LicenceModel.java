package com.example.mdapp.dto;

import java.util.Calendar;
import java.util.Date;

import com.example.mdapp.entity.ExtLicence;

import lombok.Data;

@Data
public class LicenceModel {
    /// <summary>
    /// 授权id
    /// </summary>
    public String licenceId;
    /// <summary>
    /// 1 =免费 2=销售 3=试用
    /// </summary>
    public Integer type;

    public Boolean isStop = false;

    /// <summary>
    /// 开始时间
    /// </summary>
    public Date startTime;

    /// <summary>
    /// 结束时间
    /// </summary>
    public Date endTime;

    public LicenceModel(ExtLicence extLicence) {

        // Calendar c = Calendar.getInstance();
        // c.setTime(sDate);
        // c.add(Calendar.DAY_OF_MONTH, 1);

       this.licenceId = extLicence.getLicenceId();
        this. type = extLicence.getType();
        this.isStop = extLicence.getIsStop();

        this. startTime = extLicence.getStartTime();
        this. endTime = extLicence.getEndTime();

    }
}