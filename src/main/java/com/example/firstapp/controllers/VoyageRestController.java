package com.example.firstapp.controllers;

import com.example.firstapp.entities.Voyage;
import com.example.firstapp.services.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voyages")
public class VoyageRestController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping("")
    public List<Voyage> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    @GetMapping("/{id}")
    public Voyage getVoyage(@PathVariable Long id) {
        return voyageService.getVoyageById(id)
                .orElseThrow(() -> new RuntimeException("Voyage non trouvé"));
    }

    @PostMapping("")
    public Voyage createVoyage(@RequestBody Voyage voyage) {
        return voyageService.saveVoyage(voyage);
    }

    @PutMapping("/{id}")
    public Voyage updateVoyage(@PathVariable Long id, @RequestBody Voyage voyage) {
        voyage.setId(id);
        return voyageService.updateVoyage(voyage);
    }

    @DeleteMapping("/{id}")
    public void deleteVoyage(@PathVariable Long id) {
        voyageService.deleteVoyage(id);
    }
} 