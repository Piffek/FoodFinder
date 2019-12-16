package com.example.foodFinder.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/signup")
public class RegisterController {

    @RequestMapping("/")
    public ModelAndView registerPage() {
        ModelAndView modelAndView = new ModelAndView("signup");
        return modelAndView;
    }
}
