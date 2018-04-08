package com.loc.admin.basic.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.loc.admin.basic.module.District;
import com.loc.admin.basic.repository.DistrictRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/4/7.
 */
@Service
public class DistrictService {

  private static final Integer PROVINCE_PARENT_ID = 0;
  private static final int PROVINCE_TYPE = 0;
  private static final int CITY_TYPE = 1;
  private static final int AREA_TYPE = 2;

  private static final List<District> CACHED_PROVINCE = Lists.newArrayList();
  private static final Map<Integer, List<District>> CACHED_CITY = Maps.newHashMap();
  private static final Map<Integer, List<District>> CACHED_AREA = Maps.newHashMap();

  @Autowired
  private DistrictRepository districtRepository;

  public void initCache() {
    List<District> districtList = districtRepository.findAll();
    CACHED_PROVINCE.addAll(districtList.stream()
        .filter(district -> district.getParentId().intValue() == PROVINCE_PARENT_ID.intValue())
        .collect(Collectors.toList()));

    CACHED_PROVINCE.forEach(province -> CACHED_CITY.put(province.getId(), Lists.newArrayList()));

    districtList
        .stream()
        .filter(district -> CACHED_CITY.containsKey(district.getParentId()))
        .forEach(district -> {
          CACHED_CITY
              .computeIfAbsent(district.getParentId(), v -> Lists.newArrayList())
              .add(district);
          CACHED_AREA.put(district.getId(), Lists.newArrayList());
        });

    districtList
        .stream()
        .filter(district -> CACHED_AREA.containsKey(district.getParentId()))
        .forEach(district -> CACHED_AREA
            .computeIfAbsent(district.getParentId(), v -> Lists.newArrayList())
            .add(district));
  }

  public List<District> getDistrict(Integer type, Integer parentId) {
    switch (type) {
      case PROVINCE_TYPE:
        return getProvince();
      case CITY_TYPE:
        return getCity(parentId);
      case AREA_TYPE:
        return getArea(parentId);
    }
    return null;
  }

  private List<District> getProvince() {
    return CACHED_PROVINCE;
  }

  private List<District> getCity(Integer provinceId) {
    List<District> cities = Lists.newArrayList();
    if (provinceId != null) {
      cities.addAll(CACHED_CITY.get(provinceId));
    } else {
      CACHED_CITY.values().forEach(cities::addAll);
    }
    return cities;
  }

  private List<District> getArea(Integer cityId) {
    List<District> area = Lists.newArrayList();
    if (cityId != null) {
      area.addAll(CACHED_AREA.get(cityId));
    } else {
      CACHED_AREA.values().forEach(area::addAll);
    }
    return area;
  }
}
