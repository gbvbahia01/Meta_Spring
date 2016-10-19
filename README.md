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
Run the system.



