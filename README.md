# Url Shortener

### Security Configuration
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