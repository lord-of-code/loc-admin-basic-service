package com.loc.admin.basic.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2018/4/7.
 */
@Entity
@Table(name = "city")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Integer id;


  @Column(nullable = false)
  private String name;

//  @ManyToOne
//  @JoinColumn(name = "province_id", referencedColumnName = "provinceId")
  @JsonIgnore
  @Column(nullable = false)
  private Integer provinceId;

  @Column(nullable = false)
  private Integer cityId;

}
