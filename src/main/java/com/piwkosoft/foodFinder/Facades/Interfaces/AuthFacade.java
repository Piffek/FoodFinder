package com.piwkosoft.foodFinder.Facades.Interfaces;

public interface AuthFacade {

    boolean hasAccessToPage(Long userId, String pageName);
    boolean emailUnavailable(String email);
    boolean emailsAreTheSame(String email, String repeadedEmail);

}