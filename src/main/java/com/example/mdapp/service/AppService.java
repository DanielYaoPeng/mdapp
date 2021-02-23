package com.example.mdapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.mdapp.entity.Apk;

import com.example.mdapp.repositories.AppRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    private AppRepository _appRepository ;

}
