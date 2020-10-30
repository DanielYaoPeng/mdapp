package com.example.mdapp.dto;

import lombok.Data;
import lombok.NonNull;


@Data
public class AppDeleteCacheRequest {

    @NonNull
    private String id;
}
