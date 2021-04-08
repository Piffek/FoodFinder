package com.piwkosoft.foodFinder.Core.Facades.Interfaces;

/**
 * Project: FoodFinder
 *
 * Created on: 01.04.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface AuthFacade {

  boolean hasAccessToPage(Long userId, String pageName);

  boolean emailUnavailable(String email);

  boolean emailsAreTheSame(String email, String repeadedEmail);
}
