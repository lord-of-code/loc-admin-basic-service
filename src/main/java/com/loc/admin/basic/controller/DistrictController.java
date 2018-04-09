package com.loc.admin.basic.controller;

import com.loc.admin.basic.module.District;
import com.loc.admin.basic.service.DistrictService;
import com.loc.framework.autoconfigure.springmvc.BasicResult;
import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
@RequestMapping(value = "/district")
@Slf4j
public class DistrictController {

  private final DistrictService districtService;

  @Autowired
  public DistrictController(DistrictService districtService) {
    this.districtService = districtService;
  }

  @RequestMapping(path = "getDistrict", method = RequestMethod.GET)
  public BasicResult<List<District>> getDistrict(
      @RequestParam(value = "type") @Min(0) @Max(2) Integer type,
      @RequestParam(value = "parentId", required = false) Integer parentId
  ) {
    return BasicResult.success(districtService.getDistrict(type, parentId));
  }

  @RequestMapping(path = "initCache", method = RequestMethod.PUT)
  public BasicResult initCache() {
    districtService.initCache();
    return BasicResult.success();
  }
}
