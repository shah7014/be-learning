### why springboot?

- provides lot of starter templates which will contain all the dependencies grouped together with compatible versions.
  if we use only spring then we need to manually enter all of them one by one in our build.gradle file

- provides auto configuration for lot of dependencies

- comes with embedded tomcat server. so JAR file will have tomcat server embedded

### Annotations

- @SpringBottApplication -> contains following annotation
    - @SpringBootConfiguration - this is used for telling this is a **configuration** class and beans can also be
      created inside it
    - @ComponentScan - this is used for telling spring where to look for classes whose beans need to be created
    - @EnableAutoConfiguration - this is used to auto configure some of the beans that we might require. we can also
      exclude some beans that we don't want to auto configure

- @RestController, @Service, @Repository
    - @RestController - is **different** from @Controller because its only used for REST APIs. So @RestController uses *
      *@ResponseBody internally. So by default the return of the method is bound to response body**

- @RequestBody - this is used in POST calls which will help in converting JSON to our Java object from requestbody. And
  for this conversion its **must to have noAgsConstructor, allArgsConstructor and getter-setter defined for model
  classes**

- @PathVariable
    - extracts value from URI path
    - URI -> "https://localhost:8080/departments/123"

  ```java
  public Department getDepartmentById(@PathVariable("") String id) {
  }
  ```
  
- @RequestParam
  - extracts value from query string
  - URI -> "https://localhost:8080/departments?id=123"
  ```java
  public Department getDepartmentById(@RequestParam String id) {
  }
  ```
  
- @PutMapping -  how to do an update operation?
  - first we need departemntId and the newDepartmentObject
  - so we can use **@PathVariable for departmentId** and **@RequestBody for newDepartment**
  - then its quite possible that the **newDepartment** user is sending will only contain properties that are changed
  - **Strategy** ->
    - findDepartmentById will give you the Department stored in db
    - then check the properties that are non null and update the department found
    - and then finally save the updated object


### Validations

- we will be using hibernate validator which comes in this template **spring-boot-starter-validation**
- We need to use annotation from it like **@NotBlank** in the model class
- At controller if we want to validate the **@RequestBody** we need use **@Valid**

```java
public Department saveDepartment(@RequestBody @Valid Department department) {}
```

- We have lot more annotations for validations like:-
  - @Size(min =10, max=20) => can use for **list**
  - @Length(min=10, message="String length can't be less than 10) => can use for **string**
  - @Email
  - @Positive /  @Negative
  - for dates also there are @PastOrPresent /  @FutureOrPresent


### Exception Handling

- let your exception propagate to the controller
- we will be using **@ControllerAdvice**.
- All exceptions you want to handle need to be defined as methods in **ControllerAdvice class** with **@ExceptionHandler**

```java
@ControllerAdvice
@Slf4j
public class AppExceptionHandler {

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departNotFoundExceptionHandler(DepartmentNotFoundException departmentNotFoundException) {
        log.info("DepartmentNotFoundException occurred");
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(departmentNotFoundException.getMessage())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorMessage);
    }
}
```