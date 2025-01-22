package com.example.firstapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Voyage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destination;
    private String description;
    private Date dateDepart;
    private Date dateRetour;
    private int duree;
    private double prix;
    private int nombrePlaces;
    private String typeVoyage;
    private String photoUrl;
    private String image;
    
    public Voyage() {}
    
    public Voyage(String destination, String description, Date dateDepart, 
                 int duree, double prix, int nombrePlaces, String typeVoyage) {
        this.destination = destination;
        this.description = description;
        this.dateDepart = dateDepart;
        this.duree = duree;
        this.prix = prix;
        this.nombrePlaces = nombrePlaces;
        this.typeVoyage = typeVoyage;
    }
    
    // Getters et Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getDateDepart() {
        return dateDepart;
    }
    
    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }
    
    public int getDuree() {
        return duree;
    }
    
    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    public double getPrix() {
        return prix;
    }
    
    public void setPrix(double prix) {
        this.prix = prix;
    }
    
    public int getNombrePlaces() {
        return nombrePlaces;
    }
    
    public void setNombrePlaces(int nombrePlaces) {
        this.nombrePlaces = nombrePlaces;
    }
    
    public String getTypeVoyage() {
        return typeVoyage;
    }
    
    public void setTypeVoyage(String typeVoyage) {
        this.typeVoyage = typeVoyage;
    }
    
    public Date getDateRetour() {
        return dateRetour;
    }
    
    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }
    
    public String getPhotoUrl() {
        return photoUrl;
    }
    
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
} 