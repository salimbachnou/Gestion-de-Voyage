package com.example.firstapp.repositories;

import com.example.firstapp.entities.MyUser;
import com.example.firstapp.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser(MyUser user);
    List<Reservation> findByStatut(Reservation.StatutReservation statut);
} 