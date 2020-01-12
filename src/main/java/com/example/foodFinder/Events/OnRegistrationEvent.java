package com.example.foodFinder.Events;

import com.example.foodFinder.Dto.UserEntityDTO;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
public class OnRegistrationEvent extends ApplicationEvent {
    final private UserEntityDTO userEntityDTO;
    final private Locale locale;
    final private String url;

    public OnRegistrationEvent(final UserEntityDTO userEntityDTO, final Locale locale, final String url) {
        super(userEntityDTO);

        this.userEntityDTO = userEntityDTO;
        this.locale = locale;
        this.url = url;
    }
}
