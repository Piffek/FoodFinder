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
 * <p>
 * Created on: 28.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "restaurant_entity")
public class RestaurantEntity implements Serializable {

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
      name = "restaurant_place",
      joinColumns = {@JoinColumn(name = "restaurant_id", referencedColumnName = "id")},
      inverseJoinColumns = {@JoinColumn(name = "place_type_id", referencedColumnName = "id")}
  )
  private Set<PlaceTypeEntity> types;
}