package com.piwkosoft.foodFinder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.piwkosoft.foodFinder.Controllers.RegisterController;
import com.piwkosoft.foodFinder.Facades.Interfaces.AccountFacade;
import com.piwkosoft.foodFinder.Forms.UserRegistrationForm;
import com.piwkosoft.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;
import com.piwkosoft.foodFinder.Services.Interfaces.RoleService;
import com.piwkosoft.foodFinder.Services.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;
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
@WebMvcTest(value = RegisterController.class)
public class RegisterUserTests {

  public static final String SELECT_PLAN = "standard";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserDetailsServiceImpl userDetailsService;

  @MockBean
  private AccountFacade accountFacade;

  @MockBean
  private RoleService roleService;

  private UserRegistrationForm userRegistrationFormWrongPasswords = new UserRegistrationForm();
  private UserRegistrationForm userRegistrationFormCorrect = new UserRegistrationForm();

  @BeforeEach
  public void fillWrongForm() {
    this.userRegistrationFormWrongPasswords.setAccountPlan(AccountPlan.standard.name());
    this.userRegistrationFormWrongPasswords.setCity("DDZ");
    this.userRegistrationFormWrongPasswords.setEmailAdress("patrykpiwko123412@gmail.com");
    this.userRegistrationFormWrongPasswords.setPassword("Piwko1.");
    this.userRegistrationFormWrongPasswords.setMatchingPassword("Piwko1");
  }

  @BeforeEach
  public void fillCorrectForm() {
    this.userRegistrationFormCorrect.setAccountPlan(AccountPlan.standard.name());
    this.userRegistrationFormCorrect.setCity("DDZ");
    this.userRegistrationFormCorrect.setEmailAdress("patrykpiwko123412@gmail.com");
    this.userRegistrationFormCorrect.setPassword("Piwko1.");
    this.userRegistrationFormCorrect.setMatchingPassword("Piwko1.");
  }

  @Test
  @DisplayName("show plans view")
  public void showPlansView() throws Exception {
    mockMvc.perform(get("/signup/plans")).andExpect(status().isOk());
  }

  @Test
  @DisplayName("select plan status code")
  public void selectPlan() throws Exception {
    mockMvc.perform(get("/signup/plan/{plan}/registration", SELECT_PLAN))
        .andExpect(status().isOk());
  }

  @Test
  @DisplayName("select plan model view not null")
  public void selectPlanModelIsNotNull() throws Exception {
    final ModelAndView modelAndView = mockMvc.perform(get("/signup/plan/{plan}/registration", SELECT_PLAN))
        .andExpect(status().isOk())
        .andReturn().getModelAndView();

    assertNotNull(modelAndView);
  }

  @Test
  @DisplayName("select plan model view value plan")
  public void selectPlanModelValue() throws Exception {
    final ModelAndView modelAndView = mockMvc.perform(get("/signup/plan/{plan}/registration", SELECT_PLAN))
        .andExpect(status().isOk())
        .andReturn().getModelAndView();

    assertEquals(modelAndView.getModel().get("plan"), SELECT_PLAN);
  }
}
