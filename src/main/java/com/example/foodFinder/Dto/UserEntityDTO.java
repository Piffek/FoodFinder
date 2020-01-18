package com.example.foodFinder.Dto;


import com.example.foodFinder.Persistance.Entities.NuisanceEntity;
import com.example.foodFinder.Services.AccountServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
public class UserEntityDTO {
    private Long id;
    private String name;
    private String matchingPassword;
    private String password;
    private String emailAdress;
    private String city;
    private AccountServiceImpl.AccountPlan accountPlan;
    private Set<NuisanceEntity> nuisanceEntitySet;
    private Date updatedDate;
    private Date createdDate;
}
