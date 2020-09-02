package com.piwkosoft.foodFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piwkosoft.foodFinder.Core.Controllers.LoginController;
import com.piwkosoft.foodFinder.Forms.UserLoginForm;
import com.piwkosoft.foodFinder.Core.Services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * Project: FoodFinder
 * <p>
 * Created on: 20.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = {LoginController.class})
public class LogInUserTests {

  final UserLoginForm userLoginFormWithoutPassword = new UserLoginForm();
  final UserLoginForm userLoginFormCorrect = new UserLoginForm();

  public MockMvc mockMvc;

  @MockBean
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeEach
  public void initWrongUserForm() {
    this.userLoginFormWithoutPassword.setName("admin@admin.pl");
  }

  @BeforeEach
  public void initCorrectUserForm() {
    this.userLoginFormCorrect.setName("user@user.pl");
    this.userLoginFormCorrect.setPassword("user1");
  }


  @BeforeEach
  public void initMockMvc() {
    this.mockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .build();
  }

  @Test
  @DisplayName("STATUS OK - Show login page")
  public void showLoginPage() throws Exception {
    mockMvc.perform(get("/login")).andExpect(status().isOk());
  }

  @Test
  @DisplayName("STATUS OK - logIn test")
  public void loginTest() throws Exception {
    final ObjectMapper objectMapper = new ObjectMapper();

    mockMvc.perform(post("/login", userLoginFormWithoutPassword)
        .with(csrf())
        .contentType(MediaType.TEXT_HTML)
        .content(objectMapper.writeValueAsBytes(userLoginFormWithoutPassword)))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("logIn error")
  public void resultLoginError() throws Exception {
    mockMvc.perform(
        formLogin()
            .user(userLoginFormCorrect.getName())
            .password(userLoginFormCorrect.getPassword()))
        .andExpect(forwardedUrl("/login/error"));
  }
}