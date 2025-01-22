package com.example.firstapp.controllers;

import com.example.firstapp.entities.Paiement;
import com.example.firstapp.services.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/paiements")
public class AdminPaiementController {

    @Autowired
    private PaiementService paiementService;

    @GetMapping("")
    public String listPaiements(Model model) {
        model.addAttribute("paiements", paiementService.getAllPaiements());
        return "admin/paiements";
    }

    @PostMapping("/{id}/valider")
    @ResponseBody
    public Paiement validerPaiement(@PathVariable Long id) {
        return paiementService.validerPaiement(id);
    }
} 