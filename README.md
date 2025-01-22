# Gestion de Voyage - Application Web (Spring Boot)

## Description
Cette application web permet la gestion des voyages, incluant la réservation, la gestion des utilisateurs et des paiements.
L'application est développée avec **Spring Boot** pour le backend et une interface utilisateur basée sur **Thymeleaf** ou un framework moderne comme **React/Angular/Vue.js**.

## Fonctionnalités
### Côté Admin :
- Gestion des voyages (ajout, modification, suppression)
- Gestion des utilisateurs (activation, suppression)
- Gestion des réservations (validation, annulation)
- Tableau de bord avec statistiques

### Côté Client :
- Consultation des voyages disponibles
- Réservation de voyages
- Historique des réservations
- Paiement en ligne
- Gestion du profil utilisateur

## Technologies Utilisées
- **Backend** : Spring Boot, Spring Security, Spring Data JPA, Hibernate
- **Base de données** : MySQL / PostgreSQL
- **Frontend** : Thymeleaf / React / Angular / Vue.js (selon votre choix)
- **Authentification** : JWT / OAuth 2.0
- **Paiement** : Stripe / PayPal API
- **Outils Dev** : Maven, Docker, Postman, Swagger

## Installation
### Prérequis
- JDK 21+
- Maven
- MySQL ou PostgreSQL

### Configuration
1. **Cloner le projet**
   ```sh
   git clone https://github.com/votre-repo/gestion-voyage.git](https://github.com/salimbachnou/Gestion-de-Voyage.git
   cd Gestion-de-Voyage
   ```

2. **Configurer la base de données**
   Modifier le fichier `application.properties` :
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/voyage_db
   spring.datasource.username=root
   spring.datasource.password=motdepasse
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Installer les dépendances et compiler**
   ```sh
   mvn clean install
   ```

4. **Démarrer l'application**
   ```sh
   mvn spring-boot:run
   ```

5. **Accéder à l'application**
   - API REST : `http://localhost:8080/api`
   - Interface Web : `http://localhost:8080`


## Contribution
Les contributions sont les bienvenues ! Merci de suivre ces étapes :
1. **Forker** le repo
2. Créer une branche (`feature/NouvelleFonctionnalite`)
3. Committer vos modifications
4. Pousser la branche et créer une **pull request**

## Licence
Ce projet est sous licence MIT. Voir le fichier [LICENSE](LICENSE) pour plus de détails.

