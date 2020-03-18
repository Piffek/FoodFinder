package com.example.foodFinder.Controllers;

import com.example.foodFinder.Dto.AccountPlanDTO;
import com.example.foodFinder.Dto.UserDTO;
import com.example.foodFinder.Events.OnRegisterationEvent;
import com.example.foodFinder.Facades.Interfaces.AccountFacade;
import com.example.foodFinder.Forms.UserRegistrationForm;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity.AccountPlan;
import com.example.foodFinder.Services.AccountServiceImpl;
import com.example.foodFinder.Services.Interfaces.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Project: FoodFinder
 * <p>
 * Created on: 17.03.2020
 * <p>
 * Author    : Patryk Piwko
 * <p>
 * Copyright 2020 (C) PiwkoSoft.
 */
@Controller
@RequestMapping("/signup")
public class RegisterController {

  private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

  private final ApplicationEventPublisher applicationEventPublisher;
  private final PasswordEncoder passwordEncoder;
  private final AccountFacade accountFacade;

  public RegisterController(
      final ApplicationEventPublisher applicationEventPublisher,
      final PasswordEncoder passwordEncoder,
      final AccountFacade accountFacade) {
    this.applicationEventPublisher = applicationEventPublisher;
    this.passwordEncoder = passwordEncoder;
    this.accountFacade = accountFacade;
  }

  @RequestMapping("/plans")
  public ModelAndView planSelect() {
    final ModelAndView modelAndView = new ModelAndView("plans");
    modelAndView.addObject("accountPlans", AccountPlanEntity.AccountPlan.values());
    return modelAndView;
  }

  @RequestMapping("/plan/{plan}/registration")
  public ModelAndView registration(
      @PathVariable("plan") String plan
  ) {
    final ModelAndView modelAndView = new ModelAndView("registration");
    modelAndView.addObject("plan", plan);
    return modelAndView;
  }

  @RequestMapping(value = "/plan/{plan}/regform", method = RequestMethod.GET)
  public ModelAndView registerPage(
      UserRegistrationForm userRegistrationForm,
      @PathVariable("plan") String plan) {
    final ModelAndView modelAndView = new ModelAndView("signup");

    userRegistrationForm.setAccountPlan(plan);
    modelAndView.addObject("plan", plan);
    modelAndView.addObject("userRegistrationForm", userRegistrationForm);
    return modelAndView;
  }

  @RequestMapping(value = "user-signup", method = RequestMethod.POST)
  public ModelAndView userSignUp(
      @Valid UserRegistrationForm userRegistrationForm,
      final BindingResult bindingResult,
      final WebRequest request) {
    final ModelAndView modelAndView = new ModelAndView("signup");

    if (bindingResult.hasErrors()) {
      return modelAndView;
    }

    AccountPlanDTO accountPlanDTO = accountFacade.findAccountPlanByName(
        AccountPlan.lookup(userRegistrationForm.getAccountPlan()));

    if (accountPlanDTO == null) {
      logger.debug("account plan {} not found", userRegistrationForm.getAccountPlan());
      return modelAndView;
    }

    final UserDTO userDTO = formMappingToDto(userRegistrationForm, accountPlanDTO);
    applicationEventPublisher.publishEvent(
        new OnRegisterationEvent(userDTO, request.getLocale())
    );

    return modelAndView;
  }

  private UserDTO formMappingToDto(final UserRegistrationForm userRegistrationForm,
      final AccountPlanDTO accountPlanDTO) {
    final String hashedPassword = passwordEncoder
        .encode(userRegistrationForm.getMatchingPassword());
    UserDTO userDTO = new UserDTO();
    userDTO.setCity(userRegistrationForm.getCity());
    userDTO.setEmailAdress(userRegistrationForm.getEmailAdress());
    userDTO.setUsername(userRegistrationForm.getEmailAdress());
    userDTO.setPassword(hashedPassword);
    userDTO.setAccountPlan(accountPlanDTO.getId());
    return userDTO;
  }
}