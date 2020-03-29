package com.piwkosoft.foodFinder.Listeners;

import com.piwkosoft.foodFinder.Dto.UserDTO;
import com.piwkosoft.foodFinder.Dto.VerificationTokenDTO;
import com.piwkosoft.foodFinder.Events.OnActivationEvent;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.TokenFacade;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.UserFacade;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.EmailService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 20.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class OnActivationListener implements ApplicationListener<OnActivationEvent> {

  private final UserFacade userFacade;
  private final TokenFacade tokenFacade;
  private final EmailService emailService;

  public OnActivationListener(final UserFacade userFacade,
      final TokenFacade tokenFacade,
      final EmailService emailService) {
    this.userFacade = userFacade;
    this.tokenFacade = tokenFacade;
    this.emailService = emailService;
  }

  @Override
  public void onApplicationEvent(
      final OnActivationEvent onActivationEvent) {

    final VerificationTokenDTO verificationTokenDTO = onActivationEvent.getVerificationTokenDTO();
    final UserDTO userDTO = userFacade.findById(verificationTokenDTO.getUser());
    userFacade.setAsEnable(userDTO);
    tokenFacade.setAsUsed(verificationTokenDTO);

    final String email = userDTO.getEmailAdress();

    Map<String, Object> params = new HashMap<>();
    params.put("name", email);

    emailService.sendEmail(email, params, "activation", onActivationEvent.getLocale());
  }
}
