package com.example.firstapp.controllers;

import com.example.firstapp.entities.MyUser;
import com.example.firstapp.entities.Reservation;
import com.example.firstapp.entities.Voyage;
import com.example.firstapp.services.ReservationService;
import com.example.firstapp.services.VoyageService;
import com.example.firstapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private VoyageService voyageService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String mesReservations(Authentication authentication, Model model) {
        MyUser user = userService.getUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        model.addAttribute("reservations", reservationService.getReservationsByUser(user));
        return "reservations/mes-reservations";
    }

    @PostMapping("/voyages/{voyageId}")
    public String reserverVoyage(@PathVariable Long voyageId,
                                @RequestParam int nombrePersonnes,
                                Authentication authentication,
                                RedirectAttributes redirectAttributes) {
        try {
            Voyage voyage = voyageService.getVoyageById(voyageId)
                    .orElseThrow(() -> new RuntimeException("Voyage non trouvé"));
            
            MyUser user = userService.getUserByUsername(authentication.getName())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            Reservation reservation = new Reservation();
            reservation.setVoyage(voyage);
            reservation.setUser(user);
            reservation.setNombrePersonnes(nombrePersonnes);
            reservation.setPrixTotal(voyage.getPrix() * nombrePersonnes);
            reservation.setDateReservation(new Date());
            reservation.setStatut(Reservation.StatutReservation.EN_ATTENTE);

            reservationService.saveReservation(reservation);
            redirectAttributes.addFlashAttribute("success", "Votre réservation a été enregistrée avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenue lors de la réservation: " + e.getMessage());
        }
        return "redirect:/voyages/" + voyageId;
    }

    @PostMapping("/{id}/annuler")
    public String annulerReservation(@PathVariable Long id,
                                   Authentication authentication,
                                   RedirectAttributes redirectAttributes) {
        try {
            reservationService.cancelReservation(id, authentication.getName());
            redirectAttributes.addFlashAttribute("success", "Votre réservation a été annulée avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Une erreur est survenue lors de l'annulation: " + e.getMessage());
        }
        return "redirect:/reservations";
    }
} 