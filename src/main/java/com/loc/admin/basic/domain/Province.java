package com.loc.admin.basic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2018/4/7.
 */
@Entity
@Table(name = "province")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Province implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Integer id;


  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer provinceId;

//  @OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
//  @JoinColumn(name = "province_id")
//  private Set<City> cities = new HashSet<>();

}
