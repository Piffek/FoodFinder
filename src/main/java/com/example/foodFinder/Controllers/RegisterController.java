package com.example.foodFinder.Controllers;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Events.OnRegisterationEvent;
import com.example.foodFinder.Forms.UserRegistrationForm;
import com.example.foodFinder.Services.AccountServiceImpl;
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


@Controller
@RequestMapping("/signup")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final ApplicationEventPublisher applicationEventPublisher;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(
        final ApplicationEventPublisher applicationEventPublisher,
        final PasswordEncoder passwordEncoder) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/plans")
    public ModelAndView planSelect() {
        final ModelAndView modelAndView = new ModelAndView("plans");
        modelAndView.addObject("accountPlans", AccountServiceImpl.AccountPlan.values());
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

        final UserEntityDTO userEntityDTO = formMappingToDto(userRegistrationForm);
        applicationEventPublisher.publishEvent(
                new OnRegisterationEvent(userEntityDTO, request.getLocale())
        );

        return modelAndView;
    }

    private UserEntityDTO formMappingToDto(final UserRegistrationForm userRegistrationForm) {
        final String hashedPassword = passwordEncoder.encode(userRegistrationForm.getMatchingPassword());
        UserEntityDTO userEntityDTO = new UserEntityDTO();
        userEntityDTO.setCity(userRegistrationForm.getCity());
        userEntityDTO.setEmailAdress(userRegistrationForm.getEmailAdress());
        userEntityDTO.setName(userRegistrationForm.getName());
        userEntityDTO.setPassword(hashedPassword);
        userEntityDTO.setAccountPlan(AccountServiceImpl.AccountPlan.lookup(userRegistrationForm.getAccountPlan()));
        return userEntityDTO;
    }
}