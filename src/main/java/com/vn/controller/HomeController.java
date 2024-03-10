package com.vn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/properties")
    public String properties() {
        return "properties";
    }
    @GetMapping("/services")
    public String services() {
        return "services";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/wiki")
    public String wiki() {
        return "wiki";
    }
    @GetMapping("/property_single")
    public String property_single() {
        return "property-single";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/admin/home_manager")
    public String list_user() {
        return "test";
    }
}
