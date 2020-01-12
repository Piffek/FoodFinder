package com.example.foodFinder.Listeners;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Events.OnRegistrationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnRegistrationListener implements ApplicationListener<OnRegistrationEvent> {

    @Override
    public void onApplicationEvent(OnRegistrationEvent onRegistrationEvent) {
        UserEntityDTO userEntityDTO = onRegistrationEvent.getUserEntityDTO();
    }
}
