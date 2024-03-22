# Projet fais par : SIMPORE Salathia Assalthiel
# Dock-Dorsal
 C'est une API pour une entreprise de fabrication d'équipement de correction pour les dos.
 Voila l'utilisation de l' API : 

 ## Avant toute utilisation de l'API une authentification est requise sauf pour créer un User Avec son rôle.

 # Voici les URL pour tester les différentes fonctionnalités du contrôleur UserController :

1. **Créer un utilisateur** :
   - Méthode : POST
   - URL : `/api/user`
   - Autorisation : Aucun
   - Body (JSON) : 
     ```json
     {
         "nom": "Nom de l'utilisateur",
         "prénom": "Prénom de l'utilisateur",
         "email": "user@example.com",
         "password": "motdepasse",
         "roles": "USER"
     }
     ```
      ```json
     {
         "nom": "Nom de l'utilisateur",
         "prénom": "Prénom de l'utilisateur",
         "email": "admin@example.com",
         "password": "motdepasse",
         "roles": "ADMIN"
     }
     ```

2. **Mettre à jour un utilisateur** :
   - Méthode : PUT
   - URL : `/api/user/{email}`
   - Autorisation : ADMIN
   - Remplacez `{email}` par l'email de l'utilisateur que vous souhaitez mettre à jour.
   - Body (JSON) : 
     ```json
     {
         "nom": "Nouveau nom",
         "prénom": "Nouveau prénom",
         "email": "email@example.com",
         "password": "nouveaumotdepasse",
         "roles": "USER"
     }
     ```

3. **Supprimer un utilisateur** :
   - Méthode : DELETE
   - URL : `/api/user/{email}`
   - Autorisation : ADMIN
   - Remplacez `{email}` par l'email de l'utilisateur que vous souhaitez supprimer.

4. **Obtenir un utilisateur par email** :
   - Méthode : GET
   - URL : `/api/user/{email}`
   - Autorisation : ADMIN
   - Remplacez `{email}` par l'email de l'utilisateur que vous souhaitez obtenir.

5. **Obtenir tous les utilisateurs** :
   - Méthode : GET
   - URL : `/api/users`
   - Autorisation : ADMIN

6. **Obtenir les détails de l'utilisateur actuellement connecté** :
   - Méthode : GET
   - URL : `/api/user/moi`
   - Autorisation : ADMIN ou USER

Assurez-vous de remplacer les valeurs entre accolades `{}` par les données appropriées lors de vos tests.

# Voici les URL mises à jour pour tester les différentes fonctionnalités du contrôleur CorrectionController :

1. **Soumettre une correction** :
   - Méthode : POST
   - URL : `/api/corrections`
   - Autorisation : ADMIN ou USER
   - Body (JSON) : 
     ```json
     {
         "valeurCorrection": 10.5
     }
     ```

2. **Obtenir toutes les corrections** :
   - Méthode : GET
   - URL : `/api/corrections`
   - Autorisation : ADMIN

3. **Obtenir les corrections d'un utilisateur** :
   - Méthode : GET
   - URL : `/api/corrections/{email}`
   - Autorisation : ADMIN
   - Remplacez `{email}` par l'email de l'utilisateur dont vous souhaitez obtenir les corrections.

4. **Obtenir les corrections d'un utilisateur dans une plage de dates** :
   - Méthode : GET
   - URL : `/api/corrections/{email}/date-range`
   - Autorisation : ADMIN
   - Remplacez `{email}` par l'email de l'utilisateur dont vous souhaitez obtenir les corrections.
   - Paramètres de requête :
     - `startDate` : Date de début au format `YYYY-MM-DD`.
     - `endDate` : Date de fin au format `YYYY-MM-DD`.
     - exemple : http://localhost:8080/api/corrections/date-range/moi?startDate=2024-01-01&endDate=2024-03-31

5. **Obtenir toutes les corrections dans une plage de dates** :
   - Méthode : GET
   - URL : `/api/corrections/date-range`
   - Autorisation : ADMIN
   - Paramètres de requête :
     - `startDate` : Date de début au format `YYYY-MM-DD`.
     - `endDate` : Date de fin au format `YYYY-MM-DD`.
     - exemple : http://localhost:8080/api/corrections/date-range?startDate=2024-01-01&endDate=2024-03-31

6. **Obtenir une correction spécifique par email et ID** :
   - Méthode : GET
   - URL : `/api/corrections/{email}/{correctionId}`
   - Autorisation : ADMIN 
   - Remplacez `{email}` par l'email de l'utilisateur concerné.
   - Remplacez `{correctionId}` par l'ID de la correction que vous souhaitez obtenir.

7. **Mettre à jour une correction spécifique par email et ID** :
   - Méthode : PUT
   - URL : `/api/corrections/{email}/{correctionId}`
   - Autorisation : ADMIN 
   - Remplacez `{email}` par l'email de l'utilisateur concerné.
   - Remplacez `{correctionId}` par l'ID de la correction que vous souhaitez mettre à jour.
   - Body (JSON) : 
     ```json
     {
         "valeurCorrection": 15.0
     }
     ```

8. **Supprimer une correction spécifique par email et ID** :
   - Méthode : DELETE
   - URL : `/api/corrections/{email}/{correctionId}`
   - Autorisation : ADMIN
   - Remplacez `{email}` par l'email de l'utilisateur concerné.
   - Remplacez `{correctionId}` par l'ID de la correction que vous souhaitez supprimer.

9. **Obtenir les corrections de l'utilisateur actuellement connecté dans une plage de dates** :
   - Méthode : GET
   - URL : `/api/corrections/date-range/moi`
   - Autorisation : ADMIN ou USER
   - Paramètres de requête :
     - `startDate` : Date de début au format `YYYY-MM-DD`.
     - `endDate` : Date de fin au format `YYYY-MM-DD`.

10. **Obtenir une correction spécifique de l'utilisateur actuellement connecté par ID** :
    - Méthode : GET
    - URL : `/api/corrections/moi/{correctionId}`
    - Autorisation : ADMIN ou USER
    - Remplacez `{correctionId}` par l'ID de la correction que vous souhaitez obtenir.

11. **Mettre à jour une correction spécifique de l'utilisateur actuellement connecté par ID** :
    - Méthode : PUT
    - URL : `/api/corrections/moi/{correctionId}`
    - Autorisation : ADMIN ou USER
    - Remplacez `{correctionId}` par l'ID de la correction que vous souhaitez mettre à jour.
    - Body (JSON) : 
      ```json
      {
          "valeurCorrection": 15.0
      }
      ```

12. **Supprimer une correction spécifique de l'utilisateur actuellement connecté par ID** :
    - Méthode : DELETE
    - URL : `/api/corrections/moi/{correctionId}`
    - Autorisation : ADMIN ou USER
    - Remplacez `{correctionId}` par l'ID de la correction que vous souhaitez supprimer.

Assurez-vous de remplacer les valeurs entre accolades `{}` par les données appropriées lors de vos tests.