# Semaine 41 ![Red flag](../docs/images/flags/red.svg)

## Travail réalisé

Durant la semaine 42, nous avons implémenté nos 3 scénarios d'utilisations principaux du coté client. L'application est capable de communiquer avec le serveur afin de lui transmettre les demandes. Le serveur est capable de traiter les requetes entrantes et d'en prendre compte dans une moindre mesure (mise a jour de db, réponse a l'estimation mockée..)
Nous avons également mis en place Jenkins pour l'aspect intégration continue du projet.

## Travail prévu

Pour la prochaine iteration, nous avons prévu de terminer notre POC, en vérifiant nos 3 scénarios à travers notre application, en partant du client jusqu'a l'estimation et la réservation d'un trajet. L'intégration sera terminée avec la version conceptuelle de notre variante (estimation du volume approximatif).

# Semaine 41 ![Orange flag](../docs/images/flags/orange.svg)

## Travail réalisé

Durant la semaine 41, nous avons peaufiné notre vision quant au projet, au diagramme de composants et plus généralement a son architecture. Nous avons également implémenté la base de nos composants afin d'avoir un socle sur lequel développer nos services et le projet. Enfin, une partie de la semaine a été passé sur la compréhension et des essais techniques sur plusieurs outils nous permettant la mise en oeuvre de l'option que nous avons choisi, afin d'en comprendre les possibilités et les limites et de l'implémenter par la suite.

## Travail prévu

Pour la prochaine iteration, nous souhaitons mettre en place le coté client avec une application caduque et limité a une interface très simpliste permettant de communiquer avec le coté serveur et traitement de donnée de notre projet, afin d'avoir toutes les composantes mises en place et pour pouvoir enchainer sur des cas d'utilisations de bout en bout. Nous allons également continuer a développer la partie serveur

## Problèmes rencontrés et risques

Pour cette itération, la difficulté est l'implémentation de la partie d'identification des objets et ce qui est lié plus généralement a l'estimation du volume, avec notamment l'utilisation d'outil que nous connaissons très peu, ce qui nous ralenti dans notre développement

# Semaine 40 ![Green flag](../docs/images/flags/green.svg)

## Travail réalisé

Durant la semaine 40, nous avons principalement réflechi au diagramme de composants de notre systeme ainsi que les interfaces qu'ils implémentent. Nous avons également planifié notre conduite de projet jusqu'a la démonstration de notre POC afin d'avoir une idée claire de nos objectifs. Ce planning sera par la suite rectifié et agrémenté chaque semaine.

## Travail prévu

Pour la prochaine iteration, nous avons choisi de démarrer le développement de notre POC en nous concentrant sur 3 Users cases que nous avons choisi et qui représentent au mieux notre extension.
Il a également été établi que nous allions réfléchir pour l'implémentation future de l'intelligence de notre systeme, notamment dans les choix des technologies a utiliser pour avoir un systeme fonctionnel. Ces reflexions seront portées principalement sur l'identification d'objet dans une image puis estimation du volume requis.

## Problèmes rencontrés et risques

Le principal problème rencontré vient du diagramme de composants. Nous avons eu du mal à choisir certains composants, notamment au niveau de notre extension. Les choix que nous avons fait peuvent devenir contraignant à l'avenir si nous n'arrivons pas a améliorer cette partie.


# Semaine 39 ![Orange flag](../docs/images/flags/orange.svg)

## Travail réalisé

Nous avons choisi l'extension 3 (Volume assessment: Snap a picture of your apartment, get an estimate of the volume to be moved).
Nous avons définit les personnas ainsi que les cas d'utilisations.
On a également décrit les trois scénarios qui nous semblait être les plus pertinents vis-à-vis de notre choix d'extension.

## Travail prévu

Dans un premier temps, on prévoit de compléter et de détailler davantage les scénarios.
Ensuite, on fera un premier diagramme de composant.
On commencera également à faire des recherches sur le traitement d'image et l'évaluation des distances à partir d'une photo.

## Problèmes rencontrés et risques

