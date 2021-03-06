package com.loc.admin.basic.module;

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
@Table(name = "district")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class District implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Integer parentId;

  @Column(nullable = false)
  private String initial;

  @Column(nullable = false)
  private String initials;

  @Column(nullable = false)
  private String pinyin;

  @Column(nullable = false)
  private String extra;

  @Column(nullable = false)
  private String suffix;

  @Column(nullable = false)
  private String code;

  @Column(nullable = false)
  private String areaCode;

  @JsonIgnore
  @Column(nullable = false)
  private Integer order;

}
