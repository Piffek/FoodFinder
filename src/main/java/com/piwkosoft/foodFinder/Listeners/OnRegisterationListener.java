package com.piwkosoft.foodFinder.Listeners;

import com.piwkosoft.foodFinder.Core.Constranits;
import com.piwkosoft.foodFinder.Dto.UserDTO;
import com.piwkosoft.foodFinder.Dto.VerificationTokenDTO;
import com.piwkosoft.foodFinder.Events.OnRegisterationEvent;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.TokenFacade;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.UserFacade;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.EmailService;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 *
 * Created on: 16.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class OnRegisterationListener implements ApplicationListener<OnRegisterationEvent> {

  private static final Logger logger = LoggerFactory.getLogger(OnRegisterationListener.class);

  private final EmailService emailService;
  private final UserFacade userFacade;
  private final TokenFacade tokenFacade;

  public OnRegisterationListener(final EmailService emailService,
      final UserFacade userFacade,
      final TokenFacade tokenFacade) {
    this.emailService = emailService;
    this.userFacade = userFacade;
    this.tokenFacade = tokenFacade;
  }

  @Override
  public void onApplicationEvent(final OnRegisterationEvent onRegisterEvent) {
    final UserDTO userDTO = (UserDTO) onRegisterEvent.getUserEntityDTO();
    final VerificationTokenDTO verificationTokenDTO = tokenFacade.generateSecretToken();
    userDTO.setActivatedToken(verificationTokenDTO.getId());
    userFacade.createUser(userDTO);
    logger.debug("created user {}", userDTO.getUsername());

    final String email = userDTO.getEmailAdress();
    final Map<String, Object> params = new HashMap<>();
    params.put("name", email);
    params.put("activate_url", Constranits.BASIC_URL + "/activate-token/" + verificationTokenDTO.getToken());

    emailService.sendEmail(email, params, "registration", onRegisterEvent.getLocale());
  }
}