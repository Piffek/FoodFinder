package com.example.foodFinder.Persistance.Entities;

import com.example.foodFinder.Constranits;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Calendar;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.persistence.CascadeType;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "verification_token")
public class VerificationTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenExpiryDate; //current + 30m

    @OneToOne(mappedBy = "activatedToken", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserEntity user;

    @PrePersist
    private void token() {
        setToken();
        setExpirationTokenDate();
    }

    private void setToken() {
        try {
            final SecretKey secretKey = KeyGenerator.getInstance("AES").generateKey();
            this.token = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void setExpirationTokenDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Timestamp(calendar.getTime().getTime()));
        calendar.add(Calendar.MINUTE, Constranits.EXPIRATION_TOKEN_TIME);
        this.tokenExpiryDate = new Date(calendar.getTime().getTime());
    }
}
