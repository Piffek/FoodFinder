package com.piwkosoft.foodFinder.Controllers;

import com.piwkosoft.foodFinder.Dto.VerificationTokenDTO;
import com.piwkosoft.foodFinder.Events.OnActivationEvent;
import com.piwkosoft.foodFinder.Facades.Interfaces.TokenFacade;
import com.piwkosoft.foodFinder.Facades.Interfaces.UserFacade;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 14.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Controller
@RequestMapping("/activate-token")
public class ActivationController {

  private static final Logger logger = LoggerFactory.getLogger(ActivationController.class);

  private final TokenFacade tokenFacade;
  private final UserFacade userFacade;
  private final MessageSource messageSource;
  private final ApplicationEventPublisher applicationEventPublisher;

  public ActivationController(final TokenFacade tokenFacade,
      final UserFacade userFacade,
      final MessageSource messageSource,
      final ApplicationEventPublisher applicationEventPublisher) {
    this.tokenFacade = tokenFacade;
    this.userFacade = userFacade;
    this.messageSource = messageSource;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @RequestMapping("/{token}")
  public ModelAndView activate(@PathVariable("token") String token, final WebRequest request) {
    ModelAndView modelAndView = new ModelAndView("index");
    final VerificationTokenDTO verificationTokenDTO = tokenFacade.getByToken(token);

    if (verificationTokenDTO == null) {
      modelAndView.addObject("error",
          messageSource
              .getMessage("activate.user.token.not.found", null, request.getLocale()));
      logger.error("no result for token {}", token);
      return modelAndView;
    }

    if(verificationTokenDTO.isUsed()) {
      logger.info("token {} was used", token);
      return modelAndView;
    }

    if (verificationTokenDTO.getTokenExpiryDate().before(new Date())) {
      modelAndView.addObject("error",
          messageSource
              .getMessage("activate.user.token.expired.date", null, request.getLocale()));
      logger.error("expiration date [{}] for token {} ended",
          verificationTokenDTO.getTokenExpiryDate(), token);
      return modelAndView;
    }

    applicationEventPublisher.publishEvent(new OnActivationEvent(verificationTokenDTO, request.getLocale()));

    modelAndView.addObject("sucess",
        messageSource
            .getMessage("activate.user.success", null, request.getLocale()));
    return modelAndView;
  }

}
