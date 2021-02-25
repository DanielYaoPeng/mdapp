package com.example.mdapp.service;

import com.example.mdapp.dto.AppDto;
import com.example.mdapp.dto.AppEntityType;
import com.example.mdapp.dto.EntityInfo;
import com.example.mdapp.dto.LicenceModel;
import com.example.mdapp.entity.Apk;
import com.example.mdapp.entity.ApkStatusEnum;
import com.example.mdapp.entity.AppEntity;
import com.example.mdapp.entity.BaseAppConfigItem;
import com.example.mdapp.repositories.ApkRepositories;
import com.example.mdapp.repositories.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
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

    public Map<String, List<AppDto>> GetBySheet(List<String> worksheetIds) {
        Map<String, List<AppDto>> result = new HashMap<>();
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
            if (oldapks != null) {
                var dirApps = AppBusinessModelChange(oldapks, appSections, true);
                result.put(sheetId, dirApps);
            } else {
                result.put(sheetId, null);
            }

        }
        return result;
    }

    private List<AppDto> AppBusinessModelChange(List<Apk> apkEntities, List<AppEntity> appSetions, Boolean sheetInfo) {
        List<AppDto> result = new ArrayList<>();
        for (Apk appDto : apkEntities) {
            List<String> worksheetIds = new ArrayList<>();
            List<BaseAppConfigItem> entities = new ArrayList<>();
            if (sheetInfo) {

                List<AppEntity> appSection = appSetions.parallelStream().filter(x -> appDto.getApps().contains(x.id))
                        .collect(Collectors.toList());
                appSection.forEach(x -> entities.addAll(x.getCfgs()));
                worksheetIds = entities.parallelStream().filter(x -> x.getWsType() == AppEntityType.Worksheet.getValue())
                        .map(a -> a.getWsid()).collect(Collectors.toList());
                AppDto entity = new AppDto();
                entity.setAppId(appDto.getApkid());
                entity.setAppName(appDto.getApkName());
                entity.setApkStatus(appDto.getStatus());
                entity.setAppNamePinyin(appDto.getApkNPY());
                entity.setAppSectionIds(appDto.getApps());
                entity.setCeateTime(appDto.getCtime());
                entity.setColor(appDto.getColor());
                entity.setCreateAccountId(appDto.getCaid());

                entity.setDescription(appDto.getDescription());
                entity.setDistributeId(appDto.getDistributeId());
                entity.setGoodsId(appDto.getGoodsId());
                entity.setIcon(appDto.getAvatar());

                if(appDto.getExtLicence()!=null){
                    var licence = appDto.getExtLicence().parallelStream().map(item -> {
                        LicenceModel iteModel = new LicenceModel(item);
                        return iteModel;
                    }).collect(Collectors.toList());
                    entity.setLicence(licence);
                }

                // entity.setIconUrl(iconUrl); todo 待完善，读取配置
                entity.setIsGoodsStatus(CheckAppStatusForMAP(entity.getLicence()));

                entity.setWorksheetIds(worksheetIds);
                entity.setProjectId(appDto.getPid());
                entity.setUpdateTime(appDto.getUtime());
                entity.setSourceProjectId(appDto.getSourceProjectId());

                var worksheetInfos =  entities.parallelStream().map(item->{
                    EntityInfo itemModel =new EntityInfo(item.getWsid(),item.getDsName(),item.getWsType(),item.getIcon(), appDto.getColor());
                    return itemModel;
                }).collect(Collectors.toList());

                entity.setInfos(worksheetInfos);
                result.add(entity);
            }
        }
        return result;
    }

    public Boolean CheckAppStatusForMAP(List<LicenceModel> licences) {
        if (licences == null || licences.size() == 0)
            return true;
        var now = new Date();

        if (licences.parallelStream()
                .anyMatch(x -> x.getStartTime().before(now) && x.getEndTime().after(now) && !x.getIsStop())) {

            return true;
        } else {
            return false;
        }
    }
}
