package com.example.foodFinder.Listeners;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Events.OnRegisterationEvent;
import com.example.foodFinder.Services.Interfaces.EmailService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnRegisterationListener implements ApplicationListener<OnRegisterationEvent> {

  private final EmailService emailService;

  public OnRegisterationListener(
      final EmailService emailService) {
    this.emailService = emailService;
  }

  @Override
  public void onApplicationEvent(OnRegisterationEvent onRegisterEvent) {
    UserEntityDTO userEntityDTO = (UserEntityDTO) onRegisterEvent.getUserEntityDTO();
    Map<String, Object> params = new HashMap<>();
    params.put("name", userEntityDTO.getEmailAdress());

    emailService.sendEmail(Collections.singletonList("patrykpiwko123412@gmail.com"),
        params, "registration", "registration");
  }
}