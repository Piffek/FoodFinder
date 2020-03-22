package com.piwkosoft.foodFinder.Controllers;

import com.piwkosoft.foodFinder.Forms.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  @Autowired
  private final MessageSource messageSource;

  public LoginController(final MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @RequestMapping("/login")
  public ModelAndView login(final UserLoginForm userLoginForm) {
    ModelAndView modelAndView = new ModelAndView("login");
    modelAndView.addObject("userLoginForm", userLoginForm);
    return modelAndView;
  }

  @RequestMapping("/login/error")
  public ModelAndView error(final UserLoginForm userLoginForm, final WebRequest request) {
    ModelAndView modelAndView = new ModelAndView("login");
    modelAndView.addObject("userLoginForm", userLoginForm);
    modelAndView.addObject("error",
        messageSource.getMessage("loggin.failure", null, request.getLocale()));
    return modelAndView;
  }
}
