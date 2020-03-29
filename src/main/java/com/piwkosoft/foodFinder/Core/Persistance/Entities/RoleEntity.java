package com.piwkosoft.foodFinder.Core.Persistance.Entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 16.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
@Setter
@Entity
@Table(name = "role")
public class RoleEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Role name;

  @ManyToMany(mappedBy = "roles")
  private Set<UserEntity> user;

  public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private String name;

    Role(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }
}
