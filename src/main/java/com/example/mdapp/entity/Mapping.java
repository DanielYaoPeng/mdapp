package com.example.mdapp.entity;

import lombok.Data;

@Data
public class Mapping {

    private String originalId;

    private String newId;

    public Mapping(String originalId, String newId) {
        this.originalId = originalId;
        this.newId = newId;
    }
}
