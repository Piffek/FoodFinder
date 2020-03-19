package com.piwkosoft.foodFinder.Dto;


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