# gestion-messagerie
Le but du projet c'est la mise en place d'un proxy dynamique lors de l'appel d'un service REST permettant une réplication des données entre les serveurs lancés.

Le service proposé : une simple gestion de messagerie, inscription, authentification, evoie de messages et lectures de messages envoyés et reçus.

gestion-messagerie-core    : contient la gestion des données.
gestion-messagerie-service : contient l'interface REST, et le la classe ServeurApp pour le lancement d'un serveur.
gestion-messagerie-client  : contient le client JAVA (sous forme d'une console) donnant accès au fonctionnalités du service REST lancé par les serveurs.
