package com.example.foodFinder.Persistance.Services.Interfaces;

public interface AuthService {

    boolean hasAccessToPage(Long userId, String pageName);
    boolean emailUnavailable(String email);
    boolean emailsAreTheSame(String email, String repeadedEmail);

}
