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
 * <p>
 * Created on: 24.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class EmailPropertyConf {

  @Bean
  @ConditionalOnProperty(name = "foodfinder.email", havingValue = "dev")
  public MailProperties mailProperties() {
    final MailProperties mailProperties = new MailProperties();
    mailProperties.setHost("127.0.0.1");
    mailProperties.setPort(25);
    mailProperties.getProperties().put("mail.smtp.auth", "false");
    mailProperties.getProperties().put("mail.smtp.starttls.enable", "false");
    return mailProperties;
  }

  @Bean
  public JavaMailSender javaMailSender() {
    return new JavaMailSenderImpl();
  }
}
