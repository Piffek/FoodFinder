package com.example.foodFinder.Controllers;

import com.example.foodFinder.Forms.UserLoginForm;
import javax.validation.Valid;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private final UserDetailsService userDetailsService;

    public LoginController(
        final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping("/login")
    public ModelAndView login(UserLoginForm userLoginForm) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("userLoginForm", userLoginForm);
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView userLogin(@Valid UserLoginForm userLoginForm) {
        ModelAndView modelAndView = new ModelAndView("login");
        UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginForm.getEmail());

        return modelAndView;
    }

}
