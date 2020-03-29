package com.piwkosoft.foodFinder.Core.Persistance.Entities;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    private String username;

    private String password;

    private String emailAdress;

    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_plan_id")
    private AccountPlanEntity accountPlan;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_roles",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<RoleEntity> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private Set<NuisanceEntity> nuisanceEntitySet;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @JoinColumn(name = "token")
    @OneToOne(fetch = FetchType.LAZY)
    private VerificationTokenEntity activatedToken;

    private boolean enabled;

    @PrePersist
    private void initEnable() {
        this.enabled = false;
        this.createdDate = new Date();
    }

    @PreUpdate
    private void initUpdatedDate(){
        updatedDate = new Date();
    }
}
