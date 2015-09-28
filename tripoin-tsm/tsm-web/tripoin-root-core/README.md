# Run Configurations

#####Configuration file :
* tripoin-core-integration\src\main\webapp\WEB-INF\properties-files\resources-mysql.properties
* tripoin-core-integration\src\main\webapp\WEB-INF\properties-files\resources-integration.properties

#####Command line :
* tripoin-root-core module

> ``mvn clean install -DskipTests``
 
* tripoin-core-integration module

> ``Run As -> Run Jetty``
> ``Web Application Port Default : 8081``

#####Run test for development :
* [http://localhost:8081/tripoin/wscontext/connection](http://localhost:8081/tripoin/wscontext/connection "wscontext/connection")
	* Content-Type: application/json
	* Accept: application/json
* Test For JUnit Class in tripoin-web-service module :
  *com.tripoin.web.test.WebServiceGETTest*