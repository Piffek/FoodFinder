package com.example.foodFinder.Dto;


import com.example.foodFinder.Persistance.Entities.AccountPlanEntity;
import com.example.foodFinder.Persistance.Entities.NuisanceEntity;
import com.example.foodFinder.Persistance.Entities.RoleEntity;
import com.example.foodFinder.Persistance.Entities.VerificationTokenEntity;
import com.example.foodFinder.Services.AccountServiceImpl;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String emailAdress;
    private String city;
    private Long accountPlan;
    private Set<Long> roles;
    private Date updatedDate;
    private Date createdDate;
    private Long activatedToken;
    private boolean enabled;
}