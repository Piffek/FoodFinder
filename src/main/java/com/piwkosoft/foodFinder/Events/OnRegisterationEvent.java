package com.piwkosoft.foodFinder.Events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * Project: FoodFinder
 *
 * Created on: 16.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Getter
public class OnRegisterationEvent extends ApplicationEvent {

  final private Locale locale;
  final private Object userEntityDTO;

  public OnRegisterationEvent(final Object userEntityDTO, final Locale locale) {
    super(userEntityDTO);

    this.locale = locale;
    this.userEntityDTO = userEntityDTO;
  }
}
