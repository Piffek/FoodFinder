package com.example.foodFinder.Controllers;

import com.example.foodFinder.Facades.Interfaces.UserDetailsFacade;
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

    private final UserDetailsFacade userDetailsFacade;

    public LoginController(
        final UserDetailsFacade userDetailsFacade) {
        this.userDetailsFacade = userDetailsFacade;
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
        UserDetails userDetails = userDetailsFacade.loadUserByUsername(userLoginForm.getEmail());

        return modelAndView;
    }

}
