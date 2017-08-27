# Concept Proof (Spring 4)
This is a small system with Spring 4, Hybernate 4 (JPA), PrimeFaces 5 e Maven 3 running at Tomcat and ActiveMQ for QUEUE.   
I am doing a small app about the integration those frameworks.   

## Setup
Run the DB.sql in a Postgres db.   
    * If you will use another database you must setup applicationContext.xml for this DB.   

1.  Put the Postgres Jar in the CATALINA_HOME/lib.
2.  Setup CATALINA_HOME/conf/server.xml with resource  Database Connection Pool (DBCP) to be used by application:

```xml
    <GlobalNamingResources>
        ...
                      
        <Resource name="jdbc/postgresdb" 
                      auth="Container"
                      type="javax.sql.DataSource"
                      driverClassName="org.postgresql.Driver"
                      url="jdbc:postgresql://localhost:5432/postgres"
                      removeAbandonedOnBorrow="true"
                      removeAbandonedOnMaintenance="true"
                      timeBetweenEvictionRunsMillis="10000"
                      removeAbandonedTimeout="60"
                      logAbandoned="true"
                      username="postgres" 
                      password="123456"
                      maxTotal="20"
                      maxIdle="10"
                      maxWaitMillis="-1"/>
        ...
    </GlobalNamingResources>
```
3.  Download and install [ActiveMQ](http://activemq.apache.org)
4.  Update the pom property *apachemq-version* if you downloaded a version of ApacheMQ different of 5.15.0
5.  Build the application.
6.  Run the application.

## API REST
Links to call and some parameters to test create and update.   
### User by ID   (GET)
http://localhost:8080/Spring/rest/usr/{id}

### All Users   (GET)
http://localhost:8080/Spring/rest/usrs

### Create User   (POST)
http://localhost:8080/Spring/rest/usr/create   
Body: (Create UserPermission and Department before and update json values)
```json
{  
   "name":"REST",
   "description":"REST DESC",
   "idPermissions":[  
      1,
      2
   ],
   "idDepartment":1
} 
```
### Update User   (POST)
http://localhost:8080/Spring/rest/usr/update   
Body:  
```json 
{  
   "id":3,
   "name":"REST",
   "description":"REST DESC UPDATED",
   "idPermissions":[  
      1,
      2
   ],
   "idDepartment":1
} 
```

### Delete User by ID   (POST)
http://localhost:8080/Spring/rest/usr/delete/{id}


### Create a Department (POST)
*This Rest service uses a JMS queue to save new Departments.*
*The Spring configuration of the JMS was made using a Configuration class instead Spring XML*
*Look at package **br.com.guilherme.bahia.volvo.spring.jms.setup** to see all JMS Spring setup*

http://localhost:8080/Spring/rest/dep/create
Body:  
```json 
{  
   "name":"DEP_REST_JMS",
   "description":"REST JMS DESC UPDATED"
} 