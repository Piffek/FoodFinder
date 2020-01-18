package com.example.foodFinder.Persistance.Entities;

import com.example.foodFinder.Services.AccountServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.PostConstruct;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String emailAdress;

    private String city;

    @Enumerated
    private AccountServiceImpl.AccountPlan accountPlan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntitySet")
    private Set<NuisanceEntity> nuisanceEntitySet;

    private Date updatedDate;

    private Date createdDate;

    private boolean enabled;

    @PostUpdate
    private void initUpdatedDate(){
        updatedDate = new Date();
    }

    @PostConstruct
    private void initEnable() {
        this.enabled = false;
    }
    @PostConstruct
    private void initCreatedDate(){
        this.createdDate = new Date();
    }
}
