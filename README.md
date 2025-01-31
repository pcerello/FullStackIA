#
#                    Projet Ynov FullStack
#                 Application "PisteMortelle"
#         Enquête policière générée par l'IA Ollama
#
# Groupe: Pauline Cerello | Rania Masdoua | Maximilien Auclair
# 
# Dépot Git : https://github.com/pcerello/FullStackIA/
#

Pré-requis :

- Avoir installé MySql 8.0 et s'assurer que le service mysql80 tourne
- Avoir installé Node.js
- Avoir installé JDK version 17 minimum
- Avoir Ollama d'installé : https://ollama.com/download/OllamaSetup.exe


Installation du jeu :


- Lancer dans une console : <ollama run llama3.2>

Sur IntelliJ :

- Ouvrir le dossier "Back" du projet

- Editer une configuration de Runner :
    Edit Configurations -> Add New Configuration -> Spring Boot 

- Changer le mot de passe de la base de données:
    Dans la configuration du runner Java spécifier:
    Environement variables : password=mot_de_passe_de_votre_DB;user=nom_utilisateur_de_votre_DB

- Via l'interface Database de IntelliJ :
    New -> Data Source -> MySQL -> Saisir le nom d'utilisateur et le mot de passe de votre DB et tester la connexion
    Cliquer droit sur la connexion créée -> New -> Schema -> le nommer : "pistes_mortelles" 

- Lancer le projet Java sur le dossier "Back" en faisant un Run

Sur VSCode :

- Ouvrir le dossier "front"

- Lancer le client :
        - Commande <npm i>
        - Puis commande <npm run start>

- Aller sur http://localhost:3000 (ou autre port du start npm)


Le jeu :

- Sur la page d'accueil, vous pouvez retrouver l'historique des 5 dernières enquètes enregsitrées (si il y en a)

- Ou lancer une nouvelle partie
    - Entrer un thème pour une nouvelle enquête
    - Attendez la réponse de Ollama (cela peut être très long...)

- Vous serez redirigé sur une page "Game" sur laquelle vous avez :
    - L'histoire générée
    - Possibilité de faire appel à un témoin de l'histoire
    - La possibilité de voir l'ensemble des témoignages (si il y en a eu)
    - Porter une accusation
    - Possibilité d'abandonner la partie

- Quand vous aurez porté une accusation, vous serez redirigé sur la page "Résultat"
    - Sur laquelle vous aurez le verdict