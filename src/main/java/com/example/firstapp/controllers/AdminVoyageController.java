package com.example.firstapp.controllers;

import com.example.firstapp.entities.Voyage;
import com.example.firstapp.services.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/voyages")
public class AdminVoyageController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping("")
    public String gestionVoyages(Model model) {
        model.addAttribute("voyages", voyageService.getAllVoyages());
        return "admin/voyages";
    }

    @PostMapping("/add")
    @ResponseBody
    public Voyage addVoyage(@RequestBody Voyage voyage) {
        return voyageService.saveVoyage(voyage);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public Voyage updateVoyage(@PathVariable Long id, @RequestBody Voyage voyage) {
        voyage.setId(id);
        return voyageService.updateVoyage(voyage);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public void deleteVoyage(@PathVariable Long id) {
        voyageService.deleteVoyage(id);
    }
} 