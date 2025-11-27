# Dockerfile pour UEA Student Services
# Utilise une image OpenJDK 17 pour Spring Boot

FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR de l'application
# Note: Assurez-vous d'avoir construit le projet avec 'mvn clean package' d'abord
COPY target/student-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port 8080
EXPOSE 8080

# Variables d'environnement pour la base de données
# Ces valeurs peuvent être surchargées lors du lancement du conteneur
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/student
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=12345678

# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]

