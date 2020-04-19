package com.piwkosoft.foodFinder.Core;

import org.springframework.data.relational.core.sql.In;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 14.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
public class Constranits {

  public final static String BASIC_URL = "http://localhost:78";
  public final static String DEFAULT_LOCALE = "pl";
  public final static int EXPIRATION_TOKEN_TIME = 30;
  public final static String ANONYMOUS_USER_NAME = "anonymousUser";
  public final static String DEFAULT_EMAIL_PROPERTY_FILE = "email";
  public final static String PLACE_API_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?key=AIzaSyBo5Q8La6Y8ZbBLsUk-x-jAV0Zt7hQNPQg";
  public final static String RESTAURANT_DOWNLOAD_CRON = "0 30 16 * * ?"; //01:01 AM
}
