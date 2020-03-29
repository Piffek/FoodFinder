package com.piwkosoft.foodFinder.Core.Services.Interfaces;

import com.piwkosoft.foodFinder.Core.Persistance.Entities.VerificationTokenEntity;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 13.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public interface TokenService {
  VerificationTokenEntity generateSecretToken();
  VerificationTokenEntity findByToken(String token);
  VerificationTokenEntity findById(Long id);
  void update(VerificationTokenEntity verificationTokenEntity);
}
