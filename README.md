# java-spring-boot-person-project
Java Spring Boot Person Project

This project is to create 5 RESTful APIs to perform the following actions in PostgreSQL database.
1. Add a person
2. Get all people
3. Get 1 person
4. Delete 1 person
5. Update person name

The following is the application structure.

Request -> (HTTP:GET/POST/DELETE/PUT) API layer/Controller layer -> Service layer -> Data Access layer -> PostgreSQL database

Instruction on running testings by using POSTMAN.
1. Add a person

HTTP:POST localhost:8080/api/v1/person

Headers: Key = Content-Type, Value = application/json

Body: { "name":"Peter Smith" }

Expected testing result: person will be added into PostgreSQL database and person id will be returned from response.

2. Get all people

HTTP:GET localhost:8080/api/v1/person

Headers: Key = Content-Type, Value = application/json

Expected testing result: all people data from PostgreSQL database will be shown in response.

3. Get 1 person

HTTP:GET: localhost:8080/api/v1/person/{person_id}

Headers: Key = Content-Type, Value = application/json

Expected testing result: person data will be shown in response based on provided person id.

4. Delete 1 person

HTTP:DELETE: localhost:8080/api/v1/person/{person_id}

Headers: Key = Content-Type, Value = application/json

Expected testing result: a person with provided person id will be removed from PostgreSQL database.

5. Update person name

HTTP:PUT: localhost:8080/api/v1/person/{person_id}

Headers: Key = Content-Type, Value = application/json

Body: { "name":"Sam Smith" }

Expected testing result: a person name will be updated from PostgreSQL database based on provided person id.
