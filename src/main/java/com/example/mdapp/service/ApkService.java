package com.example.mdapp.service;

import com.example.mdapp.entity.Apk;
import com.example.mdapp.entity.ApkStatusEnum;
import com.example.mdapp.entity.AppEntity;
import com.example.mdapp.repositories.ApkRepositories;
import com.example.mdapp.repositories.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class ApkService {

    @Autowired
    private ApkRepositories apkRepositories;
    @Autowired
    private AppRepository appRepository;

    private final ApkCacheService cache;

    public ApkService(ApkCacheService cache) {
        this.cache = cache;
    }

    public Apk getById(String apkId) {

        var result = cache.get(apkId);
        if (result != null)
            return result;

        result = apkRepositories.get(apkId);
        if (result.getStatus() == ApkStatusEnum.IsDeleted.getValue())
            return null;
        cache.set(apkId, result);
        return result;
    }

    public boolean clearApkCache(String apkId) {
        return cache.delete(apkId);
    }

    public Map<String, List<Apk>> GetBySheet(List<String> worksheetIds) {
        Map<String, List<Apk>> result = new HashMap<>();
        List<AppEntity> appSections = appRepository.GetBySheetId(worksheetIds);
        List<String> sectionIds = appSections.parallelStream().map(x -> x.id).collect(Collectors.toList());
        var appInfos = apkRepositories.getAppEntityForAppSectionIds(sectionIds);
        for (var sheetId : worksheetIds) {
            var section = appSections.parallelStream()
                    .filter(x -> x.getCfgs().parallelStream().anyMatch(c -> c.wsid.equals(sheetId))).findFirst()
                    .orElse(null);
            var oldapks = appInfos.parallelStream()
                    .filter(x -> x.getApps().parallelStream().anyMatch(c -> c.equals(section.id.toString())))
                    .collect(Collectors.toList());
            result.put(sheetId, oldapks);
        }
        return result;
    }
}
