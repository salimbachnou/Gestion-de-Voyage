package com.example.firstapp.controllers;

import com.example.firstapp.services.VoyageService;
import com.example.firstapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VoyageService voyageService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String showDashboard(Model model) {
        // Statistiques générales
        model.addAttribute("totalVoyages", voyageService.countTotalVoyages());
        model.addAttribute("totalReservations", voyageService.countTotalReservations());
        model.addAttribute("totalRevenue", voyageService.calculateTotalRevenue());
        model.addAttribute("totalUsers", userService.countTotalUsers());
        
        // Derniers voyages ajoutés
        model.addAttribute("recentVoyages", voyageService.getRecentVoyages());
        
        return "admin/dashboard";
    }
} 