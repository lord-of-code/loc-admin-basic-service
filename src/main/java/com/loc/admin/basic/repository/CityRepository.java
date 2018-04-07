package com.loc.admin.basic.repository;

import com.loc.admin.basic.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created on 2018/4/7.
 */
public interface CityRepository extends JpaRepository<City, Integer>,
    JpaSpecificationExecutor<City> {

}
