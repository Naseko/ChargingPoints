# ChargingPoints
 Service for charging vehicles

## Authorization
This implimentation has two inMemory stored users: ADMIN/ADMIN and USER/USER

To start the project
- mvn clean package
- docker compose up

Configured URL is http://localhost:11487/ or http://localhost:8081 without docker
JDBC URL for H2 database is jdbc:h2:mem:testdb Login and password sa

- Charging Point API.postman_collection.json file at the root of the project contains examples of REST Requests
- Other then requered, this implementation contains several endpoints for admin role to play with the database
- resources/migration folder contains some migrations, as an example of what could they be in a production environment. 
- But due to the autogeneration of all actually needed schemas in this test implementation - migrations are turned off;