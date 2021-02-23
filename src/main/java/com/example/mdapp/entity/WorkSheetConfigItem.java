package com.example.mdapp.entity;

import lombok.Data;

import java.util.Objects;

@Data
public class WorkSheetConfigItem {

    public Integer wsType;

    public String wsid ;

    public Integer vtype ;


    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WorkSheetConfigItem)) {
            return false;
        }
        var model = (WorkSheetConfigItem)obj ;
        return Objects.equals(model.wsid,this.wsid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(getWsid());
    }
   // public String getWsid(){
     //   if(this.vtype == AppType.Worksheet.getValue())
     //       wsid = super.correlationId;
    //    return wsid;
   // }
}
