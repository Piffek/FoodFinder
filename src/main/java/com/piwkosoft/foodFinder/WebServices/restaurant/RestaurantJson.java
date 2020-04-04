package com.piwkosoft.foodFinder.WebServices.restaurant;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.piwkosoft.foodFinder.Core.Constranits;
import com.piwkosoft.foodFinder.WebServices.CustomJson;
import com.piwkosoft.foodFinder.WebServices.restaurant.RestaurantJson.JsonRestaurant;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 29.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class RestaurantJson implements CustomJson<JsonRestaurant.RestaurantList> {

  public static String BASE_URL = Constranits.RESTAURANT_API_URL + "&query=restaurants+in+";

  private final RestTemplate restTemplate;

  public RestaurantJson(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public JsonRestaurant.RestaurantList objectFromJson(final String url) {
    return restTemplate.getForObject(
        url,
        JsonRestaurant.RestaurantList.class
    );
  }

  @Override
  public String returnNextPageToken(final JsonRestaurant.RestaurantList restaurants) {
    return restaurants.getNextPageToken();
  }

  @Override
  public boolean hasNextPage(final String token) {
    return token != null;
  }

  @Getter
  @Setter
  @Accessors(chain = true)
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public static class JsonRestaurant {

    private String name;
    private String[] types;
    private String formattedAddress;
    private String icon;
    private boolean isOpen;
    private Double rating;
    private BigDecimal userRatingsTotal;

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public static class RestaurantList {

      private String nextPageToken;
      private JsonRestaurant[] results;
    }
  }
}
