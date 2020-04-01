package com.piwkosoft.foodFinder.Core.Persistance.Entities;

import com.piwkosoft.foodFinder.Core.Constranits;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.PrePersist;
import javax.persistence.Table;
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
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "verification_token")
public class VerificationTokenEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private boolean used;

    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenExpiryDate; //current + 30m

    @OneToOne(mappedBy = "activatedToken", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity user;

    @PrePersist
    private void token() {
        this.used = false;
        setToken();
        setExpirationTokenDate();
    }

    private void setToken() {
        this.token = UUID.randomUUID().toString();
    }

    private void setExpirationTokenDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, Constranits.EXPIRATION_TOKEN_TIME);
        this.tokenExpiryDate = new Date(calendar.getTime().getTime());
    }
}
