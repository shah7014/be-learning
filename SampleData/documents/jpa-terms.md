### spring-data-jpa vs jpa vs hibernate

- **spring-data-jpa** =>
  - is an abstraction layer on top of JPA to reduce the amount of boilerplate code required to implement (DAO data access object)
  - so as we know it will help in **autoconfiguring** a lot of stuff, so we don't need to care about things like entityManager, transaction etc
- **JPA**
  - is just an specification that all **ORMs** follow for mappeing java entity to db columns
- **Hibernate**
  - is the **ORM implementation**
  - it generated SQL query and execute it using **JDBC driver**

### persist

- adding a new entity instance to **persistence context**
- by invoking this method we inform the JPA provider (like hibernate) that entity and persisted to db at an appropriate time
- When is appropriate time?
  - when the **transaction is committed**
  - when the **flush operation** is called explicitly

```java
EntityManager entityManager; 
EntityTransaction transaction = entityManager.getTransaction();
transaction.begin();
Useruser = new User("John Doe");
entityManager.persist(user); // adding use entity to persistence context
transaction.commit(); // entity added to db when transaction completes
```

### flush

- by default JPA flushes changes at the end of transaction
- but sometimes we want to **flush** the changes to db even before the transaction completes
- **flushing changes triggers immediate synchronization of pending changes with the db**


### Primary Key GenerationType

- IDENTITY:- relies on auto increment database column. will create a new value for id for every insert operation
- SEQUENCE:- use database sequence to generate PK values
- TABLE:- It will generate a table with two columns. First column will contain the entity name and second column will contain the value of PK. Let's say we have **5 entities, then we will have 5 rows with each row showing what's the PK for it**