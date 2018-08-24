# Url Shortener

I have created this project in a hobby to be able to test some of the technologies myself not only
during creation but also further not only during creation but also during the expansion of it with new functionalities.

So the main goal was to create a working project with a connection to a real database, initialization scheme
of the database, configured JWT authentication and some working REST API endpoints, to be able to use it as a template for other
applications developing by myself. 

What's more, it's often more valuable when we learn new technologies on an application that we've made ourselves from scratch,
and we also get more satisfaction when this app actually has some business value.

So I can now test or implement things like:
* different databases
* docker and clustering with docker swarm
* travis (with different profiles, h2 db on travis and postgres locally?)
* global exception handling and different ways of exception handling at all
* etc.

### PostgreSQL vs. Redis ?
I though about implementing url shortening with Redis like key-value database but I am not sure about it
currently I do not need saving shortened url at all because I am just encoding it 
and decoding when user use shortened url. Scaling PostgreSQL is ok and probably I do not need Redis here,
but, maybe I will do alternative version with Redis just for fun.

### Implementation details WIP

#### Security Configuration
Some notes about spring security configuration classes. [Source - Calli Coder](https://www.callicoder.com/spring-boot-spring-security-jwt-mysql-react-app-part-2/).
1. **@EnableWebSecurity**   
Primary spring security annotation that is used to enable web security in a project.
2. **@EnableGlobalMethodSecurity**  
This is used to enable method level security based on annotations.
* **secusecuredEnabled** - It enables the **@Secured** annotation
```java
@Secured("ROLE_ADMIN")
public User getAllUsers() {}
```
* **jsr250Enabled**: It enables the **@RolesAllowed** annotation that can be used like this -
```java
@RolesAllowed("ROLE_ADMIN")
public Poll createPoll() {}  
```
* **prePostEnabled**: It enables more complex expression based access control syntax with **@PreAuthorize** and **@PostAuthorize** annotations -
```java
@PreAuthorize("isAnonymous()")
public boolean isUsernameAvailable() {}

@PreAuthorize("hasRole('USER')")
public Poll createPoll() {}
```