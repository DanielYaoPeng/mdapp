package com.example.mdapp.dto;

import lombok.Data;

@Data
public class UserInfos {
    public String accountId;

    public String fullName;

    public String avatar;
    public Boolean Owner = false;
}
