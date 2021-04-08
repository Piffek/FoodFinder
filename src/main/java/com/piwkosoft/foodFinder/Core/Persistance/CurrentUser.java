package com.piwkosoft.foodFinder.Core.Persistance;

import com.piwkosoft.foodFinder.Core.Constranits;
import com.piwkosoft.foodFinder.Dto.UserDTO;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.UserFacade;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Project: FoodFinder
 *
 * Created on: 20.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class CurrentUser {

  private final UserFacade userFacade;

  public CurrentUser(final UserFacade userFacade) {
    this.userFacade = userFacade;
  }

  public UserDTO getLoggedUser() {
    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    final String username = (String) authentication.getPrincipal();

    if (!Constranits.ANONYMOUS_USER_NAME.equals(username)) {
      return userFacade.findByUsername(username);
    }

    return null;
  }
}
