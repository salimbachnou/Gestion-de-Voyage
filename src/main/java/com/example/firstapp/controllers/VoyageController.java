package com.example.firstapp.controllers;

import com.example.firstapp.entities.Voyage;
import com.example.firstapp.services.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class VoyageController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping("/voyages")
    public String listVoyages(Model model) {
        List<Voyage> voyages = voyageService.getAllVoyages();
        model.addAttribute("voyages", voyages);
        return "voyages";
    }

    @GetMapping("/voyages/{id}")
    public String voyageDetail(@PathVariable Long id, Model model) {
        return voyageService.getVoyageById(id)
                .map(voyage -> {
                    model.addAttribute("voyage", voyage);
                    return "voyage-detail";
                })
                .orElse("redirect:/voyages");
    }
} 