package com.example.foodFinder.Events;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Persistance.Entities.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;
    private Locale locale;
    private UserEntityDTO user;

    public OnRegistrationCompleteEvent(
            final UserEntityDTO user, final Locale locale, final String appUrl) {
        super(user);

        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
