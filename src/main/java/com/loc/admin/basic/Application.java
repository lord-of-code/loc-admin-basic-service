package com.loc.admin.basic;

import com.loc.admin.basic.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created on 2018/4/7.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {

  @Autowired
  private DistrictService districtService;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) {
    districtService.initCache();
  }
}
