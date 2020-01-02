package com.example.foodFinder.Controllers;

import com.example.foodFinder.Requests.UserRegistrationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/signup")
public class RegisterController {

    @RequestMapping("/")
    public ModelAndView registerPage(UserRegistrationRequest userRegistrationRequest) {
        ModelAndView modelAndView = new ModelAndView("signup");
        return modelAndView;
    }

    @RequestMapping(value="user-signup", method = RequestMethod.POST)
    public ModelAndView userSignUp(
            @Valid UserRegistrationRequest userRegistrationRequest,
            BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("signup");

        return modelAndView;
    }
}
