package com.piwkosoft.foodFinder.Core.Services.Interfaces;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface EmailService {
    void sendEmail(final List<String> recipients, final Map<String, Object> message, final String templateName, final Locale locale);
    void sendEmail(final String recipient, final Map<String, Object> message, final String templateName, final Locale locale);
}
