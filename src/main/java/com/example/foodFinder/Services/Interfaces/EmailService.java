package com.example.foodFinder.Services.Interfaces;

import java.util.List;
import java.util.stream.Stream;

public interface EmailService {
    void sendEmail(List<String> toList, String message);
}
