package com.example.foodFinder.Persistance.Entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

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

  private String name;

  @ManyToMany(mappedBy = "roles")
  private Set<UserEntity> user;

  public enum Roles {
    USER("USER"),
    ADMIN("ADMIN");

    private String name;

    Roles(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }
}
