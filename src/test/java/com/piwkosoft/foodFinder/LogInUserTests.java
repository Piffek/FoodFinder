package com.piwkosoft.foodFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piwkosoft.foodFinder.Controllers.LoginController;
import com.piwkosoft.foodFinder.Forms.UserLoginForm;
import com.piwkosoft.foodFinder.Persistance.Entities.AccountPlanEntity;
import com.piwkosoft.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;
import com.piwkosoft.foodFinder.Persistance.Entities.UserEntity;
import com.piwkosoft.foodFinder.Services.UserDetailsServiceImpl;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
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

  public final UserEntity userEntity = new UserEntity();

  public MockMvc mockMvc;

  @MockBean
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @BeforeEach
  public void initMockMvc() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @Test
  @DisplayName("Show login page - STATUS OK")
  public void showLoginPage() throws Exception {
    mockMvc.perform(get("/login")).andExpect(status().isOk());
  }

  @Test
  @DisplayName("logIn test")
  public void loginTest() throws Exception {
    final UserLoginForm userLoginForm = new UserLoginForm();
    userLoginForm.setName("admin");
    userLoginForm.setPassword("admin");

    final ObjectMapper objectMapper = new ObjectMapper();

    mockMvc.perform(post("/login", userLoginForm)
    .contentType(MediaType.TEXT_HTML)
    .content(objectMapper.writeValueAsBytes(userLoginForm)))
    .andExpect(status().isOk());

  }
}
