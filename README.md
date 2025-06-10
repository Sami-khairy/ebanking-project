# E-Banking Application

Une application bancaire moderne développée avec Angular et Spring Boot.

## 🚀 Fonctionnalités

- **Tableau de bord** : Visualisation des statistiques bancaires
  - Nombre total de clients
  - Nombre total de comptes
  - Solde total
  - Nombre total de transactions
  - Distribution des types de comptes
  - Tendances des transactions

- **Gestion des Clients**
  - Liste des clients
  - Ajout de nouveaux clients
  - Modification des informations clients
  - Suppression de clients
  - Recherche de clients

- **Gestion des Comptes**
  - Création de comptes
  - Consultation des soldes
  - Historique des transactions

- **Sécurité**
  - Authentification des utilisateurs
  - Gestion des sessions
  - Protection des routes

## 🛠️ Technologies Utilisées

### Frontend
- Angular 17
- Bootstrap 5
- Chart.js
- Bootstrap Icons
- RxJS

### Backend
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven

## 📋 Prérequis

- Node.js (v18 ou supérieur)
- Java JDK 17 ou supérieur
- Maven
- MySQL

## 🚀 Installation

### Backend

1. Cloner le repository
```bash
git clone [URL_DU_REPO]
cd ebanking-project
```

2. Configurer la base de données
- Créer une base de données MySQL nommée `ebanking`
- Configurer les paramètres de connexion dans `application.properties`

3. Lancer le backend
```bash
cd ebanking-backend
mvn spring-boot:run
```

### Frontend

1. Installer les dépendances
```bash
cd ebanking-frontend
npm install
```

2. Lancer l'application
```bash
ng serve
```

3. Accéder à l'application
```
http://localhost:4200
```

## 🔧 Configuration

### Backend
Le fichier `application.properties` contient les configurations suivantes :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ebanking
spring.datasource.username=root
spring.datasource.password=root
```

### Frontend
Le fichier `environment.ts` contient la configuration de l'API :
```typescript
export const environment = {
  production: false,
  backendHost: 'http://localhost:8080'
};
```

## 📁 Structure du Projet

```
ebanking-project/
├── ebanking-backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/ebanking/
│   │   │   │       ├── controllers/
│   │   │   │       ├── services/
│   │   │   │       ├── repositories/
│   │   │   │       └── models/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
└── ebanking-frontend/
    ├── src/
    │   ├── app/
    │   │   ├── components/
    │   │   ├── services/
    │   │   └── models/
    │   ├── assets/
    │   └── environments/
    ├── package.json
    └── angular.json
```

## 👥 Contribution

1. Fork le projet
2. Créer une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Commit vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Push vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrir une Pull Request

## 📝 Licence

Ce projet est sous licence MIT. Voir le fichier `LICENSE` pour plus de détails.

## 📧 Contact

Pour toute question ou suggestion, n'hésitez pas à ouvrir une issue ou à contacter l'équipe de développement. 