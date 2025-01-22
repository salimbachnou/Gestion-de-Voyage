package com.example.firstapp.repositories;

import com.example.firstapp.entities.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByStatut(Paiement.StatutPaiement statut);
} 