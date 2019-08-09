# DB-TO-RestAPI
The data is not directly exposed from the database to the downstream system directly for the data analytics. Instead it is exposed through Rest API End-Point. The application is developed using Spring Boot


Download Eclipse from https://www.eclipse.org/downloads/
 
Import the project in Eclipse and Add the mysql JDBC connector. Download the connector from - https://dev.mysql.com/downloads/connector/j/5.1.html
 
Build the project through command prompt - 
<Project Home Dir>mvnw clean package
 
Run the application - 
<Project Home Dir>java -jar target/DBToRestUtility-0.0.1-SNAPSHOT.jar
 
Testing - 
Testing in Browser http://localhost:8083/transaction?ts=2019-06-02%2015:00:01.100010
