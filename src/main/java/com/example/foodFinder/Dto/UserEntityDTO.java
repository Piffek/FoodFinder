package com.example.foodFinder.Dto;


import com.example.foodFinder.Persistance.Entities.NuisanceEntity;
import com.example.foodFinder.Services.AccountServiceImpl;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserEntityDTO {
    private Long id;
    private String name;
    private String password;
    private String emailAdress;
    private String city;
    private String street;
    private String postCode;
    private AccountServiceImpl.AcountType accountType;
    private Set<NuisanceEntity> nuisanceEntitySet;
    private Date updatedDate;
    private Date createdDate;
}