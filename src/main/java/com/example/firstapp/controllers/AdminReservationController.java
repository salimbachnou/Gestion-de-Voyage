package com.example.firstapp.controllers;

import com.example.firstapp.entities.Reservation;
import com.example.firstapp.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/reservations")
public class AdminReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("")
    public String listReservations(Model model) {
        model.addAttribute("reservations", reservationService.getAllReservations());
        model.addAttribute("statuts", Reservation.StatutReservation.values());
        return "admin/reservations";
    }

    @PostMapping("/{id}/statut")
    @ResponseBody
    public Reservation updateStatut(@PathVariable Long id, @RequestParam Reservation.StatutReservation statut) {
        return reservationService.updateStatutReservation(id, statut);
    }
} 