package com.example.firstapp.entities;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
@Table(name = "my_user")
public class MyUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 50)
    private String username;
    @Column(length = 100)
    private String password;
    @Column(length = 50)
    private String nom;
    @Column(unique = true, length = 100)
    private String email;
    private boolean enabled = true;
    
    @Column(length = 100)
    private String roles = "ROLE_USER"; // Par défaut ROLE_USER

    // Constructeurs
    public MyUser() {}

    public MyUser(String username, String password, String nom, String email, String roles) {
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.email = email;
        this.roles = roles;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<String> getRoles() {
        return Arrays.asList(this.roles.split(","));
    }

    public void setRoles(Collection<String> roles) {
        this.roles = String.join(",", roles);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}