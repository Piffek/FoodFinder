package com.piwkosoft.foodFinder.Conf.emailConfigs;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Project: FoodFinder
 *
 * Created on: 24.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class EmailPropertyConf {

  private final static String HOST = "127.0.0.1";
  private final static int PORT = 25;
  private final static String SMTP_AUTH_KEY = "mail.smtp.auth";
  private final static String TLS_KEY = "mail.smtp.starttls.enable";

  @Bean
  @ConditionalOnProperty(name = "foodfinder.email", havingValue = "dev")
  public MailProperties mailProperties() {
    final MailProperties mailProperties = new MailProperties();
    mailProperties.setHost(HOST);
    mailProperties.setPort(PORT);
    mailProperties.getProperties().put(SMTP_AUTH_KEY, "false");
    mailProperties.getProperties().put(TLS_KEY, "false");
    return mailProperties;
  }

  @Bean
  public JavaMailSender javaMailSender() {
    return new JavaMailSenderImpl();
  }
}
