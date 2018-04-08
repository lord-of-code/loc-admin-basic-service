package com.loc.admin.basic.controller;

import com.loc.admin.basic.domain.Area;
import com.loc.admin.basic.domain.City;
import com.loc.admin.basic.domain.Province;
import com.loc.admin.basic.service.AreaService;
import com.loc.framework.autoconfigure.springmvc.BasicResult;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2018/4/7.
 */
@RestController
@RequestMapping(value = "/area")
@Slf4j
public class AreaController {

  @Autowired
  private AreaService areaService;

  @RequestMapping(path = "getProvince", method = RequestMethod.GET)
  public BasicResult<List<Province>> getProvince(
      @RequestParam(value = "name", required = false) String name
  ) {
    return BasicResult.success(areaService.getProvince(name));
  }

  @RequestMapping(path = "getCity", method = RequestMethod.GET)
  public BasicResult<List<City>> getCity(
      @RequestParam(value = "provinceId", required = false) Integer provinceId,
      @RequestParam(value = "name", required = false) String name
  ) {
    return BasicResult.success(areaService.getCity(provinceId, name));
  }

  @RequestMapping(path = "getArea", method = RequestMethod.GET)
  public BasicResult<List<Area>> getArea(
      @RequestParam(value = "cityId", required = false) Integer cityId,
      @RequestParam(value = "name", required = false) String name
  ) {
    return BasicResult.success(areaService.getArea(cityId, name));
  }

  @RequestMapping(path = "initCache", method = RequestMethod.PUT)
  public BasicResult initCache() {
    areaService.initCache();
    return BasicResult.success();
  }
}
