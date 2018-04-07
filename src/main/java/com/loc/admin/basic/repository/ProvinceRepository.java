package com.loc.admin.basic.repository;

import com.loc.admin.basic.domain.Province;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 2018/4/7.
 */
public interface ProvinceRepository extends JpaRepository<Province, Integer> {

  List<Province> findByNameContaining(String name);
}
