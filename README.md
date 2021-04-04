# IN205 : projet 2

Premier lancement : `mvn clean install exec:java`.

Sans les tests (qui modifient la bd) : `mvn clean install exec:java -DskipTests`.

Puis pour lancer le serveur : `mvn clean install tomcat7:run -DskipTests`.

L'adresse est <http://localhost:8080/TP3Ensta>.

J'ai rencontré un problème lorsque je suis passé à l'exercice 5: mon servlet `DashboardServlet.java` me semble correct, mais en allant à l'adresse <http://localhost:8080/TP3Ensta/dashboard>, j'ai en permanance une erreur 404 que je n'arrive pas à corriger. Je n'ai donc pas continué à implémenter les autres servlets, ne pouvant pas les tester.
