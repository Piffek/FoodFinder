package com.piwkosoft.foodFinder.Conf;

import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

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
public class RestTemplateConf {

  @Bean
  public RestTemplate restTemplate() {
    final ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
        HttpClients.createDefault());
    return new RestTemplate(requestFactory);
  }
}
