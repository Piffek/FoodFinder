package com.example.foodFinder.Listeners;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Events.OnRegistrationCompleteEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        UserEntityDTO userEntity = onRegistrationCompleteEvent.getUser();
    }
}
