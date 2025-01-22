package com.example.firstapp.controllers;

import com.example.firstapp.services.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping("/")
    public String home(Model model, 
                      @RequestParam(required = false) String destination,
                      @RequestParam(required = false) Double minPrice,
                      @RequestParam(required = false) Double maxPrice,
                      @RequestParam(required = false) String date) {
        
        model.addAttribute("voyages", voyageService.searchVoyages(destination, minPrice, maxPrice, date));
        return "index";
    }
} 