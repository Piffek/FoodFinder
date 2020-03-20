package com.piwkosoft.foodFinder.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {

    @RequestMapping("/")
    public ModelAndView homePage() {
        return new ModelAndView("layout");
    }
}