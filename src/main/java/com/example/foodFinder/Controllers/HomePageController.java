package com.example.foodFinder.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Serializable;

@Controller
public class HomePageController implements Serializable {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }
}
