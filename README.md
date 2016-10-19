# Meta_Spring
This is a small system with Spring 4, Hybernate 4, PrimeFaces 5 e Maven 3 running at Tomcat.

I am doing a pook about the integration those frameworks.

I made the create and I am having a problem with LazeException right now in relation between User and Permission.
I tryed to configurate a Spring filter (OpenEntityManagerInViewFilter) in web.xml, setup de persistence context to EXTENDED,
set the fetch between User and Permission as EAGER, but the problem does not finish.

After finish the CRUD I will make a REST service and Unit tests.

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
         "id": 2,
         "name": "Usuario",
         "description": "usuario"
      },
            {
         "id": 5,
         "name": "Monaliza",
         "description": "Danada de Boa"
      }
   ],
   "dept":    {
      "id": 3,
      "name": "Buu",
      "description": "AAA"
   }
}

Update User
http://localhost:8080/Spring/rest/usr/update
Body:
{
	"id" : 18,
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
         "name": "Monaliza",
         "description": "Danada de Boa"
      }
   ],
   "dept":    {
      "id": 3,
      "name": "Buu",
      "description": "AAA"
   }
}

Delete User by ID
http://localhost:8080/Spring/rest/usr/delete/{id}


