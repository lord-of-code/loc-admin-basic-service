package com.loc.admin.basic.service;

import com.google.common.base.Strings;
import com.loc.admin.basic.domain.City;
import com.loc.admin.basic.domain.Province;
import com.loc.admin.basic.repository.CityRepository;
import com.loc.admin.basic.repository.ProvinceRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Created on 2018/4/7.
 */
@Service
public class AreaService {

  @Autowired
  private ProvinceRepository provinceRepository;

  @Autowired
  private CityRepository cityRepository;

  public List<Province> getProvince(String name) {
    if (Strings.isNullOrEmpty(name)) {
      return provinceRepository.findAll();
    } else {
      return provinceRepository.findByNameContaining(name);
    }
  }

  public List<City> getCity(Integer provinceId, String name) {
    Specification<City> specification = (root, query, cb) -> {
      ArrayList<Predicate> predicates = new ArrayList<>();
      if (provinceId != null) {
        predicates.add(cb.equal(root.get("province").get("provinceId"), provinceId));
      }

      if (!Strings.isNullOrEmpty(name)) {
        predicates.add(cb.like(root.get("name"), "%" + name + "%"));
      }
      return cb.and(predicates.toArray(new Predicate[]{}));
    };
    return cityRepository.findAll(specification);
  }
}
