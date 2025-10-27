# eBankService : Service Bancaire API REST & GraphQL
eBankService est un micro-service Spring Boot qui fournit une API pour la gestion des comptes bancaires. Il offre des endpoints REST et GraphQL pour effectuer des opérations CRUD sur les comptes bancaires et les clients.

## Technologies Utilisées
- Java 17
- Spring Boot 3.5.7
- Spring Data JPA
- Spring GraphQL
- Base de données H2 (en mémoire)
- Lombok
- OpenAPI/Swagger UI
- Maven

## Fonctionnalités
- Gestion des comptes bancaires (CRUD)
- Gestion des clients
- API REST et GraphQL
- Documentation OpenAPI
- Projections Spring Data REST
- Gestion des exceptions personnalisées

## Configuration
Le service est configuré pour fonctionner sur le port 8081 avec une base de données H2 en mémoire.
```
properties
spring.datasource.url=jdbc:h2:mem:accountDB
spring.h2.console.enabled=true
server.port=8081
spring.graphql.graphiql.enabled=true
```
## API Endpoints

### REST API
- `GET /bankAccounts` : Liste tous les comptes
- `GET /bankAccounts/{id}` : Récupère un compte par ID
- `POST /bankAccounts` : Crée un nouveau compte
- `PUT /bankAccounts/{id}` : Met à jour un compte
- `DELETE /bankAccounts/{id}` : Supprime un compte

### GraphQL API
- Queries :
  - `accountsList` : Liste tous les comptes
  - `bankAccountById` : Récupère un compte par ID
  - `customers` : Liste tous les clients
- Mutations :
  - `addAccount` : Ajoute un nouveau compte
  - `updateAccount` : Met à jour un compte
  - `deleteAccount` : Supprime un compte

## Installation et Démarrage

1. Cloner le repository
```
bash
git clone https://github.com/MohamedBOULAALAM/eBankService.git
```
2. Compiler le projet
```
bash
./mvnw clean install
```
3. Lancer l'application
```
bash
./mvnw spring-boot:run
```
## Accès aux Interfaces
- H2 Console : http://localhost:8081/h2-console
- Swagger UI : http://localhost:8081/swagger-ui.html
- GraphiQL : http://localhost:8081/graphiql

## Documentation API
### API REST
#### Opérations sur les Comptes Bancaires
##### 1. Consultation des Comptes
```
http
GET /bankAccounts
```
- **Description** : Récupère la liste de tous les comptes bancaires
- **Réponse** : Liste des comptes avec leurs détails
- **Format de réponse** :
```
json
[
{
"id": "string",
"createdAT": "date",
"balance": "number",
"currency": "string",
"type": "CURRENT_ACCOUNT|SAVINGS_ACCOUNT",
"customer": {
"id": "number",
"name": "string"
}
}
]
```
##### 2. Consultation d'un Compte Spécifique
```
http
GET /bankAccounts/{id}
```
- **Description** : Récupère les détails d'un compte bancaire spécifique
- **Paramètres** : 
  - `id` : Identifiant unique du compte (chaîne UUID)
- **Réponse** : Détails du compte
- **Code d'erreur** : 404 si le compte n'existe pas

##### 3. Création d'un Compte
```
http
POST /bankAccounts
```
- **Description** : Crée un nouveau compte bancaire
- **Corps de la requête** :
```
json
{
"balance": "number",
"currency": "string",
"type": "CURRENT_ACCOUNT|SAVINGS_ACCOUNT"
}
```
- **Réponse** : Compte créé avec son ID généré
- **Code de réussite** : 201 (Created)

##### 4. Mise à jour d'un Compte
```
http
PUT /bankAccounts/{id}
```
- **Description** : Met à jour les informations d'un compte existant
- **Paramètres** :
  - `id` : Identifiant du compte à modifier
- **Corps de la requête** : Mêmes champs que pour la création
- **Réponse** : Compte mis à jour
- **Codes d'erreur** : 404 si le compte n'existe pas

##### 5. Suppression d'un Compte
```
http
DELETE /bankAccounts/{id}
```
- **Description** : Supprime un compte bancaire
- **Paramètres** :
  - `id` : Identifiant du compte à supprimer
- **Code de réussite** : 204 (No Content)
- **Code d'erreur** : 404 si le compte n'existe pas




### API GraphQL
#### Queries
##### 1. Liste des Comptes
```
graphql
query {
accountsList {
id
createdAT
balance
currency
type
customer {
id
name
}
}
}
```
##### 2. Compte par ID
```
graphql
query {
bankAccountById(id: "string") {
id
balance
currency
type
}
}
```
##### 3. Liste des Clients
```
graphql
query {
customers {
id
name
bankaccounts {
id
balance
type
}
}
}
```


#### Mutations
##### 1. Ajout d'un Compte
```
graphql
mutation {
addAccount(bankAccount: {
balance: float
currency: "string"
type: "string"
}) {
id
balance
type
}
}
```
##### 2. Mise à jour d'un Compte
```
graphql
mutation {
updateAccount(
id: "string"
bankAccount: {
balance: float
currency: "string"
type: "string"
}
) {
id
balance
type
}
}
```
##### 3. Suppression d'un Compte
```
graphql
mutation {
deleteAccount(id: "string")
}
```


### Accès à la Documentation Interactive
1. **Swagger UI** (REST API)
   - URL : http://localhost:8081/swagger-ui.html
   - Permet de :
     - Consulter la documentation détaillée
     - Tester les endpoints directement depuis l'interface
     - Visualiser les modèles de données
     - Voir les codes de réponse possibles
2. **GraphiQL** (GraphQL API)
   - URL : http://localhost:8081/graphiql
   - Fonctionnalités :
     - Éditeur interactif pour tester les requêtes
     - Auto-complétion des requêtes
     - Documentation intégrée des types et champs disponibles
     - Visualisation du schéma GraphQL