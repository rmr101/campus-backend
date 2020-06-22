# campus-backend
school management system backend


## DataBase Congiguration

- Start docker image:
    
    `docker run -itd --name mysql-test -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 mysql`

- login container

    `docker exec -it mysql-test bash`

- login mysql

    `mysql -u root -p`
    input password
    
- create database
 
   `create database campus;`

## Use Swagger UI

- Step1: Start application 

- Step2: Access URL `http://localhost:8080/swagger-ui.html` in your browser
