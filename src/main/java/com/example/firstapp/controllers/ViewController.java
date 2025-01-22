package com.example.firstapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login1")
    public String login() {
        return "login";
    }
} 