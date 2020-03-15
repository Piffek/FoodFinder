package com.example.foodFinder.Listeners;

import com.example.foodFinder.Constranits;
import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Dto.VerificationTokenDTO;
import com.example.foodFinder.Events.OnRegisterationEvent;
import com.example.foodFinder.Mapper.VerificationTokenMapper;
import com.example.foodFinder.Services.Interfaces.EmailService;
import com.example.foodFinder.Services.Interfaces.UserService;
import com.example.foodFinder.Services.Interfaces.TokenService;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class OnRegisterationListener implements ApplicationListener<OnRegisterationEvent> {

  private final EmailService emailService;
  private final UserService userService;
  private final TokenService tokenService;

  public OnRegisterationListener(final EmailService emailService,
      final UserService userService,
      final TokenService tokenService) {
    this.emailService = emailService;
    this.userService = userService;
    this.tokenService = tokenService;
  }

  @Override
  public void onApplicationEvent(final OnRegisterationEvent onRegisterEvent) {
    final UserEntityDTO userEntityDTO = (UserEntityDTO) onRegisterEvent.getUserEntityDTO();
    final VerificationTokenDTO verificationTokenDTO = tokenService.generateSecretToken();
    userEntityDTO.setActivatedToken(VerificationTokenMapper.toEntity(verificationTokenDTO));
    userService.createUser(userEntityDTO);

    Map<String, Object> params = new HashMap<>();
    params.put("name", userEntityDTO.getEmailAdress());
    params.put("activate_url", Constranits.BASIC_URL+"/activate-token/"+userEntityDTO.getActivatedToken().getToken());

    emailService.sendEmail(Collections.singletonList("patrykpiwko123412@gmail.com"),
        params, "registration", "registration", onRegisterEvent.getLocale());
  }
}