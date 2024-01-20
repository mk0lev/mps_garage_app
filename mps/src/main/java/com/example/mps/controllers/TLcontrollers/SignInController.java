package com.example.mps.controllers.TLcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    public SignInController() {
    }

    @GetMapping("/login")
    public String showLoginPage(){

        return "sign_in";
    }
}
