package com.example.foodFinder.Services.Interfaces;

import java.util.List;
import java.util.Map;

public interface EmailService {
    void sendEmail(final List<String> toList, final Map<String, Object> message, final String templateName,
                   final String recipients);
}
