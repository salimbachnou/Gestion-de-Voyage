package com.example.firstapp.services;

import com.example.firstapp.entities.MyUser;
import com.example.firstapp.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUser registerNewUser(String username, String password, String nom, String email) {
        logger.info("Tentative d'inscription pour l'utilisateur: {}", username);
        
        // Vérifier si l'username existe déjà
        if (userRepository.findByUsername(username).isPresent()) {
            logger.warn("Username déjà pris: {}", username);
            throw new RuntimeException("Ce nom d'utilisateur est déjà pris");
        }

        // Vérifier si l'email existe déjà
        if (userRepository.findByEmail(email).isPresent()) {
            logger.warn("Email déjà utilisé: {}", email);
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        try {
            // Créer le nouvel utilisateur
            MyUser user = new MyUser();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setNom(nom);
            user.setEmail(email);
            user.setRoles(Arrays.asList("ROLE_USER"));
            user.setEnabled(true);

            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Erreur lors de l'enregistrement de l'utilisateur: {}", username, e);
            throw new RuntimeException("Erreur lors de l'enregistrement de l'utilisateur", e);
        }
    }

    public Optional<MyUser> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<MyUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public MyUser saveUser(MyUser user) {
        return userRepository.save(user);
    }

    public MyUser createAdminIfNotExists() {
        return userRepository.findByUsername("admin")
            .orElseGet(() -> {
                MyUser adminUser = new MyUser();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin123"));
                adminUser.setNom("Administrateur");
                adminUser.setEmail("admin@voyageapp.com");
                adminUser.setRoles(Arrays.asList("ROLE_ADMIN"));
                adminUser.setEnabled(true);
                return userRepository.save(adminUser);
            });
    }

    public long countTotalUsers() {
        return userRepository.count();
    }

    public List<MyUser> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<MyUser> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<MyUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
} 