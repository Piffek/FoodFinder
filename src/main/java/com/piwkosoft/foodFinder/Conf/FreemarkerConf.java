package com.piwkosoft.foodFinder.Conf;

import freemarker.core.HTMLOutputFormat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class FreemarkerConf {

  private final static String OUTPUT_ENCODING = "UTF-8";
  private final static String DEFAULT_ENCODING = "UTF-8";

  @Bean
  public freemarker.template.Configuration freemarkerConfiguration() {
    final freemarker.template.Configuration configuration =
        new freemarker.template.Configuration();
    configuration.setOutputFormat(HTMLOutputFormat.INSTANCE);
    configuration.setOutputEncoding(OUTPUT_ENCODING);
    configuration.setDefaultEncoding(DEFAULT_ENCODING);
    return configuration;
  }
}
