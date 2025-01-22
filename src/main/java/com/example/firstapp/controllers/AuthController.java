package com.example.firstapp.controllers;

import com.example.firstapp.entities.MyUser;
import com.example.firstapp.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@Controller
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/login-success")
    public String loginSuccess(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin";
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            return "redirect:/voyages";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String confirmPassword,
                             @RequestParam String email,
                             @RequestParam String nom,
                             Model model) {
        logger.info("Tentative d'inscription pour l'utilisateur: {}", username);
        try {
            // Vérifier si les mots de passe correspondent
            if (!password.equals(confirmPassword)) {
                logger.warn("Les mots de passe ne correspondent pas pour l'utilisateur: {}", username);
                model.addAttribute("error", "Les mots de passe ne correspondent pas");
                return "register";
            }

            // Vérifier la complexité du mot de passe
            if (password.length() < 8) {
                logger.warn("Mot de passe trop court pour l'utilisateur: {}", username);
                model.addAttribute("error", "Le mot de passe doit contenir au moins 8 caractères");
                return "register";
            }

            // Enregistrer l'utilisateur
            MyUser user = userService.registerNewUser(username, password, nom, email);
            logger.info("Utilisateur enregistré avec succès: {}", username);

            // Rediriger vers la page de connexion avec un message de succès
            return "redirect:/login1?registered=true";
            
        } catch (RuntimeException e) {
            logger.error("Erreur lors de l'inscription de l'utilisateur: {}", username, e);
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
} 