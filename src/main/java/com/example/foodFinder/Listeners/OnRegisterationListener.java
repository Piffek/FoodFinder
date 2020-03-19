package com.example.foodFinder.Listeners;

import com.example.foodFinder.Constranits;
import com.example.foodFinder.Controllers.RegisterController;
import com.example.foodFinder.Dto.UserDTO;
import com.example.foodFinder.Dto.VerificationTokenDTO;
import com.example.foodFinder.Events.OnRegisterationEvent;
import com.example.foodFinder.Facades.Interfaces.TokenFacade;
import com.example.foodFinder.Facades.Interfaces.UserFacade;
import com.example.foodFinder.Services.Interfaces.EmailService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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

    Map<String, Object> params = new HashMap<>();
    params.put("name", userDTO.getEmailAdress());
    params.put("activate_url", Constranits.BASIC_URL+"/activate-token/"+ verificationTokenDTO.getToken());

    emailService.sendEmail(Collections.singletonList("patrykpiwko123412@gmail.com"),
        params, "registration", "registration", onRegisterEvent.getLocale());
  }
}