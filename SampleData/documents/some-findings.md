### application.yml setup

- you need connection url, username, password and driver name
- spring.jpa.hibernate.ddl-auto:-
  
  - none -> in production we go with "none"
  
  - create -> hibernate first drops existing tables and then create new tables
  
  - create-drop -> similar to create but hibernate drop the database at the end of session
  
  - update -> hibernate compares the object created based on annotations with database existing columns. If something changed, it will try to update. But it will never remove a column or a constraint that existed previously

  - validate -> hibernate validates the database schema with the object schema created based on annotations

- so this property **spring.jpa.hibernate.ddl-auto is set to update in local development phase** but **in production either we set it to none or just don't use this property at all**

### why we use **Integer** as PK and not **int**

- because **int has default value of 0** and **Integer has default value of null**. So when we want to create new entity we don't want to provide any id. So **Integer with null as default** is the way to go

### @Column
- name -> if we want to define the name of column different from entity's field name
- nullable
- unique
- insertable -> useful when we are working with field like **createdAt**. So it should only be allowed to be added at insert time but not allowed to be updated
- updatable -> useful when we are working with field like **updatedAt**. So it should only be allowed to be updated and not inserted