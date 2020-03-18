package com.example.foodFinder.Facades.Interfaces;

import com.example.foodFinder.Dto.VerificationTokenDTO;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 13.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface TokenFacade {
  VerificationTokenDTO generateSecretToken();
  VerificationTokenDTO getByToken(String token);
}
