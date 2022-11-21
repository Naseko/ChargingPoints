# Charging Points V0.1.0
 Rest API for  charging vehicles service
 Вespite it was not mentioned in the task, this API contains several endpoints which can be used to add entities to the database. They will be described below.
## Installation
- Download the project from GitHub
- From project's root folder run `mvn clean package`
- Run `docker compose up`
- (Optional) To populate project's DB with some examples open H2 Console and insert some initial data from `init-data.sql` in the root of the project.

### Troubleshooting
- If the standard way is not working for any reasons - launch file `troubleshoot/chargepoints-0.1.0.jar` or run `docker-compose.yaml` from the same directory.
  <p><sup>PS: Actually it is not a good idea to store .jar files in gitlab, but in case of test challange I find it to be sutable desition to let others easily start the app without spending time on troubleshooting if some environment differences can interfere with running the project in a usual way.</sup>

## Authorization
This implementation has two in-memory stored users: 
- **admin** with password **admin**
- **user** with password **user**

To log in H2 admin console use login **sa** with password **sa**

## URLs
### Application root URL
- The default application url is configured to be http://localhost:8081

### H2 Console
- http://localhost:8081/h2-console (**Warning**: Check JDBC URL for H2 database, it should be `jdbc:h2:mem:testdb`)

### Open API documentation for the API
- http://localhost:8081/swagger-ui/index.html

## Endpoints
- `api.postman_collection.json` file at the root of the project contains examples of requests. If using `init-data.sql` examples and postman collection - to test requested endpoints - the only needed thing is to copy returned session number, while starting the new session and then use this number to stop session or to mock an error.

### Main requested endpoints:
#### Customer
- To start new session: http://localhost:8081/api/v1/customer/session/start 
- To stop the session: http://localhost:8081/api/v1/customer/session/stop (in fact this endpoint just adds stop_time to the session record and also generate random meter value)

#### Common
- To fetch the version: http://localhost:8081/version
#### Admin
- To fetch sessions: http://localhost:8081/api/v1/admin/sessions (The configured range works in that way that only returns sessions which has start_time equals or greater then given date and has stop_time quals or smaller then given time. Plus it returns sessions which are currently going on).
- To add new connector: http://localhost:8081/api/v1/admin/connector

#### Additional
- To add a customer: http://localhost:8081/api/v1/admin/customer
- To add a charging point: http://localhost:8081/api/v1/admin/point
- To add a RFID: http://localhost:8081/api/v1/admin/rfid
- To add a vehicle: http://localhost:8081/api/v1/admin/vehicle
- To generate error situation: http://localhost:8081/api/v1/admin/mockerror (in fact, this endpoint just stops a given session, placing 0 in meter value field, assuming the error stoped us from charging the car. And also creates error record).

## Question abt Unit tests for the date range filter:
- Check that only sessions with time_start equals or greater than given are selected
- Check that only sessions with time_stop equals or less than given are selected
- Check the behavior with unstopped yet sessions (in my understanding they are also must be selected, but this can be on the opposite)
- Optionally make API convert given datetime to one standard pattern and check it really converts in 

## Also...
- `resources/migration` folder contains some migrations, as an example of what could they be in a production environment. But due to the auto-generation of all actually needed schemas in this test implementation - migrations are turned off;
- The same with `static/swagger.yml` - it contains an example of API description, which can be used to generate API and its documentation, and server ot/and client parts, but not used in the project.