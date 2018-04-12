# devcon18
devcon 18 demo application

This shows a simple mass assignment vulnerability

It is made with spring boot, spring-mvc and spring security
As a simple MVC application, it uses thyemleaf for rendering.

Mass assignment can be done by adding parameters to the request. 
If these parameters match the attributes in the User object, Spring will autobind them to the user object,
thus creating an injection attack through the mass assignment vulnerabilty
