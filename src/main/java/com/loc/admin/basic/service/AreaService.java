package com.loc.admin.basic.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.loc.admin.basic.domain.Area;
import com.loc.admin.basic.domain.City;
import com.loc.admin.basic.domain.Province;
import com.loc.admin.basic.repository.AreaRepository;
import com.loc.admin.basic.repository.CityRepository;
import com.loc.admin.basic.repository.ProvinceRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/4/7.
 */
@Service
public class AreaService {

  private static final List<Province> CACHED_PROVINCE = Lists.newArrayList();
  private static final Map<Integer, List<City>> CACHED_CITY = Maps.newHashMap();
  private static final Map<Integer, List<Area>> CACHED_AREA = Maps.newHashMap();

  @Autowired
  private ProvinceRepository provinceRepository;

  @Autowired
  private CityRepository cityRepository;

  @Autowired
  private AreaRepository areaRepository;

  public void initCache() {
    CACHED_PROVINCE.addAll(provinceRepository.findAll());
    cityRepository.findAll()
        .forEach(city -> CACHED_CITY
            .computeIfAbsent(city.getProvinceId(), v -> Lists.newArrayList())
            .add(city));
    areaRepository.findAll().forEach(
        area -> CACHED_AREA.computeIfAbsent(area.getCityId(), v -> Lists.newArrayList())
            .add(area));
  }

  public List<Province> getProvince(String name) {
    if (Strings.isNullOrEmpty(name)) {
      return CACHED_PROVINCE;
    } else {
      return CACHED_PROVINCE.stream().filter(province -> province.getName().contains(name))
          .collect(Collectors.toList());
    }
  }

  public List<City> getCity(Integer provinceId, String name) {
    List<City> cities = Lists.newArrayList();
    if (provinceId != null) {
      cities.addAll(CACHED_CITY.get(provinceId));
    } else {
      CACHED_CITY.values().forEach(cities::addAll);
    }

    return cities.stream().filter(city -> {
      if(Strings.isNullOrEmpty(name)) {
        return true;
      } else {
        return city.getName().contains(name);
      }
    }).collect(Collectors.toList());

//    每次查询的访问数据库
//    Specification<City> specification = (root, query, cb) -> {
//      ArrayList<Predicate> predicates = new ArrayList<>();
//      if (provinceId != null) {
//        predicates.add(cb.equal(root.get("province").get("provinceId"), provinceId));
//      }
//
//      if (!Strings.isNullOrEmpty(name)) {
//        predicates.add(cb.like(root.get("name"), "%" + name + "%"));
//      }
//      return cb.and(predicates.toArray(new Predicate[]{}));
//    };
//    return cityRepository.findAll(specification);
  }

  public List<Area> getArea(Integer cityId, String name) {
    List<Area> areas = Lists.newArrayList();
    if (cityId != null) {
      areas.addAll(CACHED_AREA.get(cityId));
    } else {
      CACHED_AREA.values().forEach(areas::addAll);
    }

    return areas.stream().filter(area -> {
      if(Strings.isNullOrEmpty(name)) {
        return true;
      } else {
        return area.getName().contains(name);
      }
    }).collect(Collectors.toList());

//    每次查询的访问数据库
//    Specification<Area> specification = (root, query, cb) -> {
//      ArrayList<Predicate> predicates = new ArrayList<>();
//      if (cityId != null) {
//        predicates.add(cb.equal(root.get("city").get("cityId"), cityId));
//      }
//
//      if (!Strings.isNullOrEmpty(name)) {
//        predicates.add(cb.like(root.get("name"), "%" + name + "%"));
//      }
//      return cb.and(predicates.toArray(new Predicate[]{}));
//    };
//    return areaRepository.findAll(specification);
  }
}
