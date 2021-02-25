package com.example.mdapp.controller;

import com.example.mdapp.dto.AppDeleteCacheRequest;
import com.example.mdapp.dto.GetAppInfoBySheetRequest;
import com.example.mdapp.entity.Apk;
import com.example.mdapp.entity.GoodsRelation;
import com.example.mdapp.service.ApkService;
import com.example.mdapp.service.GoodsRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/app")
public class AppController {
    @Autowired
    private ApkService apkService;

    @RequestMapping("/get")
    @ResponseBody
    public Apk getById(String id) {
        if (id.isEmpty()) {
            return null;
        }
        return apkService.getById((id));
    }

    @PostMapping("/deleteCache")
    @ResponseBody
    public Boolean deleteCache(@RequestBody @Validated AppDeleteCacheRequest request) {
        var result = apkService.clearApkCache(request.getId());
        return result;
    }

    @PostMapping("/getBySheet")
    @ResponseBody
    public Map<String, List<Apk>> GetBySheet(@RequestBody GetAppInfoBySheetRequest request) {
        return apkService.GetBySheet(request.worksheetIds);
    }
}
