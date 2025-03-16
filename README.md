# URL Shortener - Spring Boot & MongoDB

## Description
Ce projet est un **raccourcisseur d'URL** développé avec **Spring Boot** et **MongoDB**. Il permet de générer des URL courtes à partir de liens longs, de rediriger les utilisateurs et de suivre les statistiques des clics.


## Installation & Exécution
### **Cloner le projet**
```bash
git clone https://github.com/votre-repo/url-shortener.git
cd url-shortener
```
### **Configurer MongoDB** (local ou Docker)

### **Configurer l'application**
Modifier `src/main/resources/application.properties` :
```properties
spring.data.mongodb.uri
server.port
```

### **Lancer l'application**
