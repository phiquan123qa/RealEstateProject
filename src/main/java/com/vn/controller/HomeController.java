package com.vn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/signin")
    public String login(){ return "signin"; }

    @GetMapping("/signup")
    public String signup(){ return "signup"; }

    @GetMapping("/recover_account")
    public String recover_account(){ return "recoverAcc"; }
}
