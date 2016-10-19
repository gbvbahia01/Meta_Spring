# Concept Proof (Spring 4)
This is a small system with Spring 4, Hybernate 4 (JPA), PrimeFaces 5 e Maven 3 running at Tomcat.

I am doing a small app about the integration those frameworks.

Next step is to do a JMS with Spring

#Setup
Setup this project is easy.
Run the DB.sql in a DB that you prefer.
Configure persistence.xml for this DB.
Update the database.properties file
Run the system.

#API REST
User by ID

http://localhost:8080/Spring/rest/usr/{id}

All Users

http://localhost:8080/Spring/rest/usrs

Create User

http://localhost:8080/Spring/rest/usr/create

Body:
{
   "name": "REST",
   "description": "REST DESC",
   "userPermissionList":    [
            {
         "id": 0,
         "name": "Usuario",
         "description": "usuario"
      },
            {
         "id": 5,
         "name": "Admin",
         "description": "Super User"  }
   ],
   "dept":    {
      "id": 3,
      "name": "Finance",
      "description": "A lot of money"
   }
}

Update User

http://localhost:8080/Spring/rest/usr/update

Body:
{
	"id" : 1,
   "name": "REST_UP_UP",
   "description": "REST DESC UP",
   "userPermissionList":    [
            {
         "id": 2,
         "name": "Usuario",
         "description": "usuario"
      },
            {
         "id": 5,
         "name": "Admin",
         "description": "Super User"
      }
   ],
   "dept":    {
      "id": 3,
      "name": "Finance",
      "description": "A lot of money"
   }
}

Delete User by ID

http://localhost:8080/Spring/rest/usr/delete/{id}


