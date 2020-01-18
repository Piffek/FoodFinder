package com.example.foodFinder.Controllers;

import com.example.foodFinder.Dto.UserEntityDTO;
import com.example.foodFinder.Events.OnRegistrationEvent;
import com.example.foodFinder.Forms.UserRegistrationForm;
import com.example.foodFinder.Services.AccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
@RequestMapping("/signup")
public class RegisterController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterController.class);

    private final MessageSource messageSource;
    private final ApplicationEventPublisher applicationEventPublisher;

    public RegisterController(final MessageSource messageSource,
                              final ApplicationEventPublisher applicationEventPublisher) {
        this.messageSource = messageSource;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @RequestMapping("/plans")
    public ModelAndView planSelect() {
        ModelAndView modelAndView = new ModelAndView("plans");
        modelAndView.addObject("accountPlans", AccountServiceImpl.AccountPlan.values());
        return modelAndView;
    }

    @RequestMapping("/plan/{plan}/registration")
    public ModelAndView registration(
            @PathVariable("plan") String plan
    ) {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("plan", plan);
        return modelAndView;
    }

    @RequestMapping("/plan/{plan}/regform")
    public ModelAndView registerPage(
            @PathVariable("plan") String plan,
            UserRegistrationForm userRegistrationForm)
    {
        ModelAndView modelAndView = new ModelAndView("signup");
        userRegistrationForm.setAccountPlan(AccountServiceImpl.AccountPlan.valueOf(plan));
        modelAndView.addObject("plan", plan);
        return modelAndView;
    }

    @RequestMapping(value = "user-signup", method = RequestMethod.POST)
    public ModelAndView userSignUp(
            @Valid final UserRegistrationForm userRegistrationForm,
            final BindingResult bindingResult,
            final WebRequest request)
    {
        ModelAndView modelAndView = new ModelAndView("signup");

        if(bindingResult.hasErrors()) {

        }

        UserEntityDTO userEntityDTO = formMappingToEntity(userRegistrationForm);
        applicationEventPublisher.publishEvent(
                new OnRegistrationEvent(userEntityDTO, request.getLocale(), request.getContextPath()));
        return modelAndView;
    }

    private UserEntityDTO formMappingToEntity(UserRegistrationForm userRegistrationForm) {
        UserEntityDTO userEntityDTO = new UserEntityDTO();
        userEntityDTO.setCity(userRegistrationForm.getCity());
        userEntityDTO.setEmailAdress(userRegistrationForm.getEmailAdress());
        userEntityDTO.setName(userRegistrationForm.getName());
        userEntityDTO.setPassword(userRegistrationForm.getPassword());
        userEntityDTO.setMatchingPassword(userRegistrationForm.getMatchingPassword());
        userEntityDTO.setAccountPlan(userRegistrationForm.getAccountPlan());
        return userEntityDTO;
    }
}