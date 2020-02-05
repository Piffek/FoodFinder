package com.example.foodFinder.Listeners;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Events.OnRegisterationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnRegisterationListener implements ApplicationListener<OnRegisterationEvent> {
    @Override
    public void onApplicationEvent(OnRegisterationEvent onRegisterEvent) {
        UserEntityDTO userEntityDTO = (UserEntityDTO) onRegisterEvent.getUserEntityDTO();
    }
}