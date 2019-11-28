package com.example.foodFinder.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

@Controller
public class HomePageController {

    @RequestMapping("/")
    public String homePage() {
        return "index";
    }
}
