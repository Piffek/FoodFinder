package com.piwkosoft.foodFinder.Conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * Project: FoodFinder
 *
 * Created on: 28.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
@Configuration
public class MessageSourceConfig {

  private final static String BASENAME = "classpath:messages";
  private final static String ENCODING = "UTF-8";

  @Bean
  public MessageSource messageSource() {
    final ReloadableResourceBundleMessageSource messageSource =
        new ReloadableResourceBundleMessageSource();
    messageSource.setBasename(BASENAME);
    messageSource.setDefaultEncoding(ENCODING);
    return messageSource;
  }
}
