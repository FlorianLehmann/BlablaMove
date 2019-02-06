# Semaine 06 ![Orange flag](../docs/images/flags/orange.svg)

## Travail réalisé

Durant la semaine 06, nous avons pu mettre en place la quasi-totalité de notre chaine, afin d'avoir notre nouveau scénario de bout en bout. Cependant, il n'est toujours pas possible de le dérouler en intégralité, il manque la mise en place de la découpe de la vidéo, chainon manquant de notre extension.

## Travail prévu

Pour la prochaine iteration, nous allons nous attarder sur la partie de découpe vidéo. Il sera alors possible de dérouler un scénario primaire a travers toutes les couches de notre architecture. Il faudra terminer par conteneuriser notre architecture afin de renforcer notre pipeline d'intégration


# Semaine 05 ![Orange flag](../docs/images/flags/orange.svg)

## Travail réalisé

Durant la semaine 05, il a été décidé d'arreter les frais sur les POCs de la partie estimation via la vidéo. L'architecture étant définie, nous avons mis l'accent sur la réalisation de plusieurs parties de cette derniere. L'application est prete a enregistrer la vidéo et a l'envoyer a un service externe de traitement ad-hoc. La partie integration reste un point sensible du projet.

## Travail prévu

Pour la prochaine iteration, nous avons prévu de créer ce service externe qui nous manque dans l'architecture avec une méthode de traitement et d'estimation basique mais qui permet d'avoir un scénario jouable intégralement et qui non seulement ne casse pas les précédents, mais vient completer les objectifs de notre extension. L'accent va également etre mis sur l'intégration afin d'avoir un pipeline complet, sur lequel viendra s'ajouter un déploiement automatisable (Docker ?) 


# Semaine 04 ![Orange flag](../docs/images/flags/orange.svg)

## Travail réalisé

Durant la semaine 04, nous avons principalement théorisé sur des POCs et des techniques pour mettre en place le support de la vidéo pour l'estimation du volume. Nous avons également préparé l'application pour supporter l'enregistrement vidéo afin de prévoir cette partie d'upload vers notre service de traitement de l'image. Nous nous sommes enfin interessé a la mise en place de l'intégration continue de facon plus poussée

## Travail prévu

Pour la prochaine iteration, nous avons prévu de mettre en place une premiere stratégie d'analyse basique, consistant a découper la vidéo toute les X secondes afin d'en extraire une image qui sera ensuite analysée par Microsoft Azure. Les résultats une fois obtenus seront regroupés et envoyé sur l'interface de l'utilisateur afin d'avoir cette premiere chaine complete validant notre nouveau scénario

# Semaine 42 ![Red flag](../docs/images/flags/red.svg)

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

