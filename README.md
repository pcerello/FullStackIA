#
#                    Projet Ynov FullStack
#                 Application "PisteMortelle"
#         Enquête poslicière générée par l'IA Ollama
#
# Groupe: Pauline Cerello | Rania Masdoua | Maximilien Auclair
# 
# Dépot Git : https://github.com/pcerello/FullStackIA/
#

Pré-requis :

- Avoir installé MySql 8.0
- Avoir installé NodeJs
- Avoir installé JDK version 17 minimum
- Avoir Ollama d'installé : https://ollama.com/download/OllamaSetup.exe


Installation du jeu :

- Créer une base de données MySql avec un schéma appelé "pistes_mortelles" 

- Changer le mot de passe de la base de données:
    Dans la configuration du runner Java spécifier:
    Environement variables : password=mot_de_passe_de_votre_DB

- Lancer dans une console : <ollama run llama3.2>

- Lancer le projet Java sur le dossier "Back"

- Lancer le serveur npm sur le dossier "Front"
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
    - Sur laquelle vous aurez le vérdict