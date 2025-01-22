package com.example.firstapp.services;

import com.example.firstapp.entities.Paiement;
import com.example.firstapp.repositories.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PaiementService {
    
    @Autowired
    private PaiementRepository paiementRepository;
    
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }
    
    public Paiement validerPaiement(Long id) {
        Paiement paiement = paiementRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Paiement non trouvé"));
        paiement.setStatut(Paiement.StatutPaiement.VALIDE);
        return paiementRepository.save(paiement);
    }
} 