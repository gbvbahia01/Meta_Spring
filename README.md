# Concept Proof (Spring 4)
This is a small system with Spring 4, Hybernate 4 (JPA), PrimeFaces 5 e Maven 3 running at Tomcat.   
I am doing a small app about the integration those frameworks.   
Next step is to do a JMS with Spring

#Setup
Setup this project is easy.   
Run the DB.sql in a DB that you prefer.   
Configure applicationContext.xml for this DB.   


Tomcat server folder CATALINA_HOME/conf/server.xml setup database JNDI to be used by application:

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

Run the system.

#API REST
Links to call and some parameters to test create and update.   
###User by ID   (GET)
http://localhost:8080/Spring/rest/usr/{id}

###All Users   (GET)
http://localhost:8080/Spring/rest/usrs

###Create User   (POST)
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
###Update User   (POST)
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

###Delete User by ID   (POST)
http://localhost:8080/Spring/rest/usr/delete/{id}


