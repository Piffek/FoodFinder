package com.piwkosoft.foodFinder.Events;

import com.piwkosoft.foodFinder.Dto.VerificationTokenDTO;
import java.util.Locale;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Project: FoodFinder
 *
 * Created on: 20.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
public class OnActivationEvent extends ApplicationEvent {

  private final VerificationTokenDTO verificationTokenDTO;
  private final Locale locale;

  public OnActivationEvent(final Object verificationTokenDTO, final Locale locale) {
    super(verificationTokenDTO);

    this.verificationTokenDTO = (VerificationTokenDTO) verificationTokenDTO;
    this.locale = locale;
  }
}
