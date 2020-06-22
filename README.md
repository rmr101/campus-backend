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
