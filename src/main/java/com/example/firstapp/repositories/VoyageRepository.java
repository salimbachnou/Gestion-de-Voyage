package com.example.firstapp.repositories;

import com.example.firstapp.entities.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    List<Voyage> findByDestinationContains(String destination);
    List<Voyage> findByPrixLessThan(double prix);
    List<Voyage> findByTypeVoyage(String typeVoyage);
    List<Voyage> findTop5ByOrderByIdDesc();
} 