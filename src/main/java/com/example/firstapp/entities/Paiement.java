package com.example.firstapp.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Paiement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Reservation reservation;
    
    private double montant;
    private Date datePaiement;
    
    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;
    
    private String reference;
    
    public enum StatutPaiement {
        EN_ATTENTE,
        VALIDE,
        REFUSE,
        REMBOURSE
    }
    
    public Paiement() {
        this.datePaiement = new Date();
        this.statut = StatutPaiement.EN_ATTENTE;
    }
    
    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Reservation getReservation() { return reservation; }
    public void setReservation(Reservation reservation) { this.reservation = reservation; }
    
    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }
    
    public Date getDatePaiement() { return datePaiement; }
    public void setDatePaiement(Date datePaiement) { this.datePaiement = datePaiement; }
    
    public StatutPaiement getStatut() { return statut; }
    public void setStatut(StatutPaiement statut) { this.statut = statut; }
    
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
} 