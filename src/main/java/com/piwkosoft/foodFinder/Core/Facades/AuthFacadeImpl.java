package com.piwkosoft.foodFinder.Core.Facades;

import com.piwkosoft.foodFinder.Core.Facades.Interfaces.AuthFacade;

public class AuthFacadeImpl implements AuthFacade {

  @Override
  public boolean hasAccessToPage(Long userId, String pageName) {
    return false;
  }

  @Override
  public boolean emailUnavailable(String email) {
    return false;
  }

  @Override
  public boolean emailsAreTheSame(String email, String repeadedEmail) {
    return false;
  }
}
