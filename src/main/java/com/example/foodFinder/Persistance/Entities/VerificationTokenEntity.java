package com.example.foodFinder.Persistance.Entities;

import lombok.Getter;
import lombok.Setter;
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

@Setter
@Getter
@Entity(name = "verification_token")
public class VerificationTokenEntity {
    private static final int EXPIRATION = 24 * 60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        DateTime dateTime = DateTime.now();
        dateTime.plusMinutes(expiryTimeInMinutes);
        return dateTime.toDate();
    }
}
