package com.piwkosoft.foodFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piwkosoft.foodFinder.Core.Controllers.RegisterController;
import com.piwkosoft.foodFinder.Core.Facades.Interfaces.AccountFacade;
import com.piwkosoft.foodFinder.Forms.UserRegistrationForm;
import com.piwkosoft.foodFinder.Core.Services.Interfaces.RoleService;
import com.piwkosoft.foodFinder.Core.Services.UserDetailsServiceImpl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 22.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(value = {RegisterController.class})
public class RegisterUserTests {

  final String SELECT_PLAN = "standard";

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @MockBean
  private UserDetailsServiceImpl userDetailsService;

  @MockBean
  private AccountFacade accountFacade;

  @MockBean
  private RoleService roleService;

  private ValidatorFactory validatorFactory;

  private List<BasicNameValuePair> userRegistrationFormWrongPasswords = new ArrayList<>();
  private List<BasicNameValuePair> userRegistrationFormCorrect = new ArrayList<>();

  @BeforeEach
  private void initMockMvc() {
    mockMvc = MockMvcBuilders
        .webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .build();
    validatorFactory = Validation.buildDefaultValidatorFactory();
  }

  @BeforeEach
  public void fillWrongForm() {
    userRegistrationFormWrongPasswords = Arrays
        .asList(
            new BasicNameValuePair("accountPlan", SELECT_PLAN),
            new BasicNameValuePair("city", "DDZ"),
            new BasicNameValuePair("emailAdress", "patrykpiwko123412@gmail.com"),
            new BasicNameValuePair("password", "Piwko1."),
            //bad matching password
            new BasicNameValuePair("matchingPassword", "Piwko1"));
  }

  @BeforeEach
  public void fillCorrectForm() {
    userRegistrationFormCorrect = Arrays
        .asList(
            new BasicNameValuePair("accountPlan", SELECT_PLAN),
            new BasicNameValuePair("city", "DDZ"),
            new BasicNameValuePair("emailAdress", "patrykpiwko123412@gmail.com"),
            new BasicNameValuePair("password", "Piwko1."),
            new BasicNameValuePair("matchingPassword", "Piwko1."));
  }

  @Test
  @DisplayName("show plans view")
  public void showPlansView() throws Exception {
    mockMvc.perform(get("/signup/plans")).andExpect(status().isOk());
  }

  @Test
  @DisplayName("STATUS 200 - select plan view")
  public void selectPlan() throws Exception {
    mockMvc.perform(get("/signup/plan/{plan}/registration", SELECT_PLAN))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("select plan model view not null")
  public void selectPlanModelIsNotNull() throws Exception {
    final ModelAndView modelAndView = mockMvc
        .perform(get("/signup/plan/{plan}/registration", SELECT_PLAN))
        .andExpect(status().isOk())
        .andReturn().getModelAndView();

    assertNotNull(modelAndView);
  }

  @Test
  @DisplayName("select plan model view value plan")
  public void selectPlanModelValue() throws Exception {
    final ModelAndView modelAndView = mockMvc
        .perform(get("/signup/plan/{plan}/registration", SELECT_PLAN))
        .andExpect(status().isOk())
        .andReturn().getModelAndView();

    assertEquals(modelAndView.getModel().get("plan"), SELECT_PLAN);
  }

  @Test
  @DisplayName("STATUS 200 - selected plan and open reg form VIEW")
  public void selectedPlanRegForm() throws Exception {
    mockMvc.perform(get("/signup/plan/{plan}/regform", SELECT_PLAN)).andExpect(status().isOk());
  }

  @Test
  @DisplayName("CORRECT - Validation form")
  public void correctFormValidation() throws Exception {
    final ModelAndView modelAndView = mockMvc.perform(post("/signup/user-signup/")
        .with(csrf())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .content(EntityUtils.toString(new UrlEncodedFormEntity(userRegistrationFormCorrect))))
        .andExpect(status().isOk())
        .andReturn().getModelAndView();

    assertNull(modelAndView.getModelMap().getAttribute("errors"));
  }

  @Test
  @DisplayName("INCORRECT - Validation form")
  public void invalidFormValidation() throws Exception {
    final ModelAndView modelAndView = mockMvc.perform(post("/signup/user-signup/")
        .with(csrf())
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .content(EntityUtils.toString(new UrlEncodedFormEntity(userRegistrationFormWrongPasswords))))
        .andExpect(status().isOk())
        .andReturn().getModelAndView();

    assertNotNull(modelAndView.getModelMap().getAttribute("errors"));

  }
}
