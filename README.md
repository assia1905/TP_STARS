# TP_Stars

- TP_Stars est une application Android qui permet d'afficher une liste de célébrités (stars) avec leurs évaluations. Les utilisateurs peuvent rechercher des célébrités, évaluer leur note et partager l'application.

## Fonctionnalités

- Affichage d'une liste de célébrités avec leurs images, noms et notes.
- Possibilité de filtrer les célébrités à l'aide d'une barre de recherche.
- Evaluation des célébrités via un système de notation (1 à 5 étoiles).
- Partage de l'application via les options de partage Android.
- Design intuitif avec une interface utilisateur moderne.

## Prérequis

- Android Studio
- JDK 8 ou supérieur
- Un appareil Android physique ou un émulateur configuré

## Installation

- Clonez le dépôt du projet :

   ```bash
   - git clone https://github.com/votre_nom_utilisateur/TP_Stars.git
- Ouvrez le projet dans Android Studio.

- Compilez le projet et lancez l'application sur un appareil physique ou un émulateur.

## Architecture du projet
- Le projet est structuré de la manière suivante :

  - com.example.tp_stars: Le package principal contenant les activités principales.
  - adapter: Contient l'adaptateur StarAdapter pour gérer l'affichage des célébrités dans le RecyclerView.
  - beans: Contient la classe Star représentant une célébrité avec ses attributs (nom, image, note).
  - service: Fournit un service pour gérer la liste des célébrités (ajout, recherche, etc.).
 ## Classes principales
 - ListActivity
   - Activité principale qui affiche la liste des célébrités. Elle permet également la recherche et le partage de l'application.

- StarAdapter
  - Un RecyclerView.Adapter utilisé pour afficher les éléments de la liste. Il gère le filtrage de la liste et l'affichage du dialogue pour noter les célébrités.

- Star
  - Une classe représentant une célébrité, avec des attributs tels que le nom, l'image, et la note.

## Demonstration en video
- Ajoutez ici des captures d'écran de l'application pour montrer l'interface utilisateur.

## Utilisation
- Lancez l'application.
- Parcourez la liste des célébrités.
- Appuyez sur une célébrité pour évaluer sa note.
- Utilisez la barre de recherche pour filtrer la liste.
- Partagez l'application via le menu.
## Améliorations futures
- Ajouter des fonctionnalités pour modifier et supprimer des célébrités.
- Implémenter une base de données locale pour stocker les données de façon persistante.
- Ajouter un design plus personnalisé pour chaque élément de la liste.
## Auteurs
- Assia BOUJNAH
