package com.example.mps.controllers.TLcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    public HomeController() {
    }

    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }
}
