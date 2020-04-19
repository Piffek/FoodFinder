package com.piwkosoft.foodFinder.Core.Controllers.Exceptions;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 19.04.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) Si-eCommerce sp. z o.o.
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

  @org.springframework.web.bind.annotation.ExceptionHandler(PlacesNotFoundException.class)
  public void notFoundException(final HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.NOT_FOUND.value());
  }
}
