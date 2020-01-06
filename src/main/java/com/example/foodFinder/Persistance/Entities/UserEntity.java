package com.example.foodFinder.Persistance.Entities;

import com.example.foodFinder.Services.AccountServiceImpl;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
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
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email_adress")
    private String emailAdress;

    @Column(name = "city")
    private String city;

    @Enumerated
    @Column(name = "accountPlan")
    private AccountServiceImpl.AccountPlan accountPlan;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntitySet")
    private Set<NuisanceEntity> nuisanceEntitySet;

    @Column(name = "updatedDate")
    private Date updatedDate;

    @Column(name = "createdDate")
    private Date createdDate;

    @PostUpdate
    private void initUpdatedDate(){
        updatedDate = new Date();
    }

    @PostConstruct
    private void initCreatedDate(){
        createdDate = new Date();
    }
}
