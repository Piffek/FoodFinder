package com.piwkosoft.foodFinder.Controllers;

import com.piwkosoft.foodFinder.Forms.UserLoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public ModelAndView login(UserLoginForm userLoginForm) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("userLoginForm", userLoginForm);
        return modelAndView;
    }
}
