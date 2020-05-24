# Coronapp

## Présentation

Projet d'une application mobile dans le cadre du cours Programmation Mobile ESIEA

Cette application affiche les pays touchés par le corona virus, les morts et les personnes qui ont survécu

## Prérequis


- Installation d'Android Studio
- Récupérer la branche Master<br/>


````
https://github.com/braseo/Coronapp.git
````

## Consignes respectées : 


- Écrans : 2 activités, 2 fragments
- Affichage d'une liste d’éléments dans une Recycler View
- Affichage du détail d'un item de la liste dans une autre activité
- Appel WebService à une API Rest
- Stockage de données en cache

- Fonctions supplémentaires :
	- Barre de recherche
	- Redirection sur les détails des pays (morts, survivants)
	- Menu avec des options cliquables, bouton paramètre et bouton exit (ferme l'application) 
	- Clean architecture & MVC
	- Singletons pour les objets
	- Gitflow



## Fonctionnalités: 

### Premier écran 

- Recycler view avec les noms des pays touchés par le coronavirus.

<img src="images/Recycler_view.png" alt="Recycler view" width="300" height="600" />

- Menu en appuyant sur les 3 points en haut à droite avec 2 options:
	
	- Settings qui affiche un toast
	- Exit qui quitte l'application
<img src="images/Item.png" alt="Item" width="300" height="600" />
	
- Barre de recherche pour trouver directement un pays
<img src="images/Search_bar.png" alt="Search_bar" width="300" height="600" />

### Ecran détail des pays, le nombre de contaminé et le nombre de mort

- Affiche le pays
- Affiche le nombre de contaminé 
- Affiche le nombre de personnes qui ont survécu au virus
- Affiche le nombre de mort
- Affiche le nombre de contaminé dans les dernières 24h
- Affiche le nombre de personnes qui ont survécu au virus dans les dernières 24h
- Affiche le nombre de mort dans les dernières 24h

<img src="images/Details_activity.png" alt="Detail_activity" width="300" height="600" />

### Notifications

Reception de notification avec Firebase Cloud Messaging.

<img src="images/Firebase.png" alt="Firebase" width="300" height="600" />

