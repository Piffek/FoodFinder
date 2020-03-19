package com.piwkosoft.foodFinder.Events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
public class OnRegisterationEvent extends ApplicationEvent {

    final private Locale locale;
    final private Object userEntityDTO;

    public OnRegisterationEvent(final Object userEntityDTO, final Locale locale) {
        super(userEntityDTO);

        this.locale = locale;
        this.userEntityDTO = userEntityDTO;
    }
}
