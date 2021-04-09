package com.piwkosoft.foodFinder.WebServices.place;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.piwkosoft.foodFinder.Core.Constranits;
import com.piwkosoft.foodFinder.WebServices.CustomJson;
import com.piwkosoft.foodFinder.WebServices.place.PlaceJson.JsonPlace.JsonPlaceList;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Project: FoodFinder
 *
 * Created on: 29.03.2020
 *
 * Author    : Patryk Piwko
 *
 * Copyright 2020 (C) PiwkoSoft.
 */
@Component
public class PlaceJson implements CustomJson<JsonPlaceList> {

  public static String BASE_URL = Constranits.PLACE_API_URL + "&query=restaurants+in+";

  private final RestTemplate restTemplate;

  public PlaceJson(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public JsonPlaceList objectFromJson(final String url) {
    return restTemplate.getForObject(url, JsonPlaceList.class);
  }

  @Getter
  @Accessors(chain = true)
  @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
  public final static class JsonPlace {

    private String name;
    private String[] types;
    private String formattedAddress;
    private String icon;
    private boolean isOpen;
    private Double rating;
    private BigDecimal userRatingsTotal;

    @Getter
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public final static class JsonPlaceList {

      private String nextPageToken;
      private JsonPlace[] results;
    }
  }
}
