package com.example.firstapp.services;

import com.example.firstapp.entities.MyUser;
import com.example.firstapp.entities.Reservation;
import com.example.firstapp.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    
    public List<Reservation> getReservationsByStatut(Reservation.StatutReservation statut) {
        return reservationRepository.findByStatut(statut);
    }
    
    public Reservation updateStatutReservation(Long id, Reservation.StatutReservation nouveauStatut) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
        reservation.setStatut(nouveauStatut);
        return reservationRepository.save(reservation);
    }
    
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
    
    public List<Reservation> getReservationsByUser(MyUser user) {
        List<Reservation> reservations = reservationRepository.findByUser(user);
        reservations.sort((r1, r2) -> r2.getDateReservation().compareTo(r1.getDateReservation()));
        return reservations;
    }
    
    public void cancelReservation(Long id, String username) {
        Reservation reservation = reservationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));
            
        // Vérifier que la réservation appartient à l'utilisateur
        if (!reservation.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Non autorisé");
        }
        
        // Vérifier la date limite d'annulation (7 jours avant le départ)
        Date limitDate = new Date(reservation.getVoyage().getDateDepart().getTime() - (7 * 24 * 60 * 60 * 1000));
        if (new Date().after(limitDate)) {
            throw new RuntimeException("L'annulation n'est possible que jusqu'à 7 jours avant le départ");
        }
        
        reservation.setStatut(Reservation.StatutReservation.ANNULEE);
        reservationRepository.save(reservation);
    }
} 