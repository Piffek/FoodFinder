package com.piwkosoft.foodFinder.Core.Persistance.Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Project: FoodFinder
 *
 * Created on: 28.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "place_entity")
public class PlaceEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String formattedAdress;
  private String icon;
  private boolean isOpen;
  private Double rating;
  private BigDecimal userRatingsTotal;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {
      CascadeType.REFRESH
  })
  @JoinTable(
      name = "place_to_type",
      joinColumns = {@JoinColumn(name = "place_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "type_id", referencedColumnName = "id")}
  )
  private Set<PlaceTypeEntity> types;
}
