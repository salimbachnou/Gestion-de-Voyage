package com.example.firstapp.services;

import com.example.firstapp.entities.Voyage;
import com.example.firstapp.repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoyageService {

    @Autowired
    private VoyageRepository voyageRepository;

    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    public Optional<Voyage> getVoyageById(Long id) {
        return voyageRepository.findById(id);
    }

    public void deleteVoyage(Long id) {
        voyageRepository.deleteById(id);
    }

    public List<Voyage> searchVoyages(String destination, Double minPrice, Double maxPrice, String date) {
        if (destination == null && minPrice == null && maxPrice == null && date == null) {
            return voyageRepository.findAll();
        }

        return voyageRepository.findAll().stream()
            .filter(v -> destination == null || v.getDestination().toLowerCase().contains(destination.toLowerCase()))
            .filter(v -> minPrice == null || v.getPrix() >= minPrice)
            .filter(v -> maxPrice == null || v.getPrix() <= maxPrice)
            .filter(v -> date == null || v.getDateDepart().toString().contains(date))
            .collect(Collectors.toList());
    }

    public Voyage updateVoyage(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    public Voyage saveVoyage(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    public long countTotalVoyages() {
        return voyageRepository.count();
    }

    public long countTotalReservations() {
        return 0; // À implémenter avec la logique de réservation
    }

    public double calculateTotalRevenue() {
        return 0.0; // À implémenter avec la logique de réservation
    }

    public List<Voyage> getRecentVoyages() {
        return voyageRepository.findTop5ByOrderByIdDesc();
    }

    // Autres méthodes selon vos besoins...
} 