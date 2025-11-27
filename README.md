# UEA Student Services - Mini Système d'Inscription aux Cours

## Description

Application Spring Boot pour la gestion des inscriptions aux cours des étudiants de l'UEA (Université de l'Evangelique en Afrique).

## Technologies Utilisées

- **Java 17**
- **Spring Boot 3.2.5**
- **Spring Data JPA**
- **MySQL 8.0**
- **HTML, Tailwindcss** (Frontend)
- **Swagger/OpenAPI 3** (Documentation API)
- **Maven** (Gestion des dépendances)

## Prérequis

- Java JDK 17 ou supérieur
- Maven 3.6+
- MySQL 8.0 ou supérieur
- (Optionnel) Docker et Docker Compose

## Installation et Lancement

### Méthode 1 : Lancement Local

Alors comme c'était ecrit dans la methodologie vous allez clone depuis votre ordinateur le projet soit via github ou telecharger le code source complet

1. **Cloner le projet**
   ```bash
   git clone https://github.com/dieudonneopilam/uea-student-app.git
   cd student
   ```

2. **Configurer la base de données MySQL**
   
   Créer une base de données nommée `student` dans MySQL :
   ```sql
   CREATE DATABASE student;
   ```


3. **Configurer les paramètres de connexion**
   
   Modifier le fichier `src/main/resources/application.properties` si nécessaire en tout cas :
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/student
   spring.datasource.username=root
   spring.datasource.password=votre_mot_de_passe
   ```

4. **Compiler et lancer l'application**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

5. **Accéder à l'application**

   - Interface Web : http://localhost:8080
   - API Swagger : http://localhost:8080/swagger-ui.html
   - API REST : http://localhost:8080/api

### Méthode 2 : Lancement avec Docker

1. **Construire l'application**
   ```bash
   mvn clean package
   ```

2. **Lancer avec Docker Compose**
   ```bash
   docker-compose up -d
   ```

3. **Accéder à l'application**
   - Interface Web : http://localhost:8080
   - API Swagger : http://localhost:8080/swagger-ui.html

## Structure du Projet

```
student/
├── src/
│   ├── main/
│   │   ├── java/com/crud/student/
│   │   │   ├── model/          # Entités JPA (Student, Course)
│   │   │   ├── repository/     # Repositories Spring Data
│   │   │   ├── service/        # Services métier
│   │   │   ├── controller/     # Contrôleurs REST et Web
│   │   │   ├── dto/            # Data Transfer Objects
│   │   │   ├── exception/      # Gestion des exceptions
│   │   │   └── config/         # Configuration (Swagger)
│   │   └── resources/
│   │       ├── templates/      # Templates HTML & Tailwindcss
│   │       ├── static/         # Fichiers statiques
│   │       └── sql/            # Requêtes SQL de test
│   └── test/                   # Tests unitaires
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Fonctionnalités

### Backend (API REST)

- ✅ Créer un étudiant (nom, post-nom, matricule, faculté, email)
- ✅ Créer un cours (code, nom, crédits, enseignant)
- ✅ Inscrire un étudiant à un cours (Relation Many-to-Many)
- ✅ Afficher la liste des cours d'un étudiant
- ✅ Afficher la liste des étudiants inscrits à un cours
- ✅ Lister tous les étudiants
- ✅ Lister tous les cours
- ✅ Trouver les cours sans étudiants
- ✅ Supprimer toutes les inscriptions d'un étudiant

### Frontend (HTML & Tailwindcss)

- ✅ Page de liste des étudiants
- ✅ Page de liste des cours
- ✅ Page d'inscription d'un étudiant à un cours
- ✅ Interface moderne et responsive

## Endpoints API

### Étudiants
- `POST /api/students` - Créer un étudiant
- `GET /api/students` - Lister tous les étudiants
- `GET /api/students/{id}` - Obtenir un étudiant par ID
- `GET /api/students/matricule/{matricule}` - Obtenir un étudiant par matricule
- `GET /api/students/course/{courseCode}` - Lister les étudiants d'un cours
- `DELETE /api/students/{id}/enrollments` - Supprimer toutes les inscriptions d'un étudiant

### Cours
- `POST /api/courses` - Créer un cours
- `GET /api/courses` - Lister tous les cours
- `GET /api/courses/{id}` - Obtenir un cours par ID
- `GET /api/courses/code/{code}` - Obtenir un cours par code
- `GET /api/courses/without-students` - Lister les cours sans étudiants

### Inscriptions
- `POST /api/enrollments` - Inscrire un étudiant à un cours

### Pages Web
- `GET /` - Page d'accueil (redirige vers /students)
- `GET /students` - Liste des étudiants
- `GET /courses` - Liste des cours
- `GET /enroll` - Formulaire d'inscription

## Modèle de Données

### Entité Student
- `id` (Long, Primary Key)
- `nom` (String)
- `postNom` (String)
- `matricule` (String, Unique)
- `faculte` (String)
- `email` (String, Unique)
- `courses` (Set<Course>, Many-to-Many)

### Entité Course
- `id` (Long, Primary Key)
- `code` (String, Unique)
- `nom` (String)
- `credits` (Integer)
- `enseignant` (String)
- `students` (Set<Student>, Many-to-Many)

### Table de Jointure
- `student_course` (student_id, course_id)

## Tests SQL

Les requêtes SQL de test sont disponibles dans `src/main/resources/sql/test-queries.sql` :

1. **Lister tous les étudiants inscrits dans un cours donné**
2. **Trouver les cours sans étudiants**
3. **Supprimer toutes les inscriptions d'un étudiant sans supprimer l'étudiant**

## Documentation API

La documentation Swagger est accessible à : http://localhost:8080/swagger-ui.html

## Docker

### Construire l'image
```bash
docker build -t uea-student-services .
```

### Lancer avec Docker Compose
```bash
docker-compose up -d
```

### Arrêter les conteneurs
```bash
docker-compose down
```

## Architecture

L'application suit les principes de l'architecture en couches :

- **Controller** : Gestion des requêtes HTTP
- **Service** : Logique métier
- **Repository** : Accès aux données
- **DTO** : Transfert de données entre les couches
- **Model** : Entités JPA

## Exemples d'Utilisation

### Créer un étudiant (API)
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{
    "nom": "Jean",
    "postNom": "Dupont",
    "matricule": "STU001",
    "faculte": "Informatique",
    "email": "jean.dupont@uea.edu"
  }'
```

### Créer un cours (API)
```bash
curl -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -d '{
    "code": "INFO101",
    "nom": "Introduction à la Programmation",
    "credits": 3,
    "enseignant": "Prof. Smith"
  }'
```

### Inscrire un étudiant à un cours (API)
```bash
curl -X POST http://localhost:8080/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{
    "studentMatricule": "STU001",
    "courseCode": "INFO101"
  }'
```

## Gestion des Erreurs

L'application utilise un gestionnaire d'exceptions global (`GlobalExceptionHandler`) qui retourne des réponses JSON structurées en cas d'erreur.

## Auteur

Dieudonné Ngwangwa Malipo

## Licence

Ce projet est un projet d'évaluation, donc Aucune licence n'est demandé. Merci

---

**Note** : Je n'ai pas envoyé la BDD donc veillez vous rassurer que MySQL est démarré et que la base de données `student` existe avant de lancer l'application.

Merci Beaucoup

# uea-student-app
