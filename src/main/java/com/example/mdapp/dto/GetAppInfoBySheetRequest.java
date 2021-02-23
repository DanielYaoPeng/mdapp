package com.example.mdapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetAppInfoBySheetRequest {
    public List<String> worksheetIds;
}
