## JR Academy Projects 3 - Campus system 1.0
This is the backend project three from JR Academy. 
Objective of this project is to deliver a learning management system for an educational institution.

### What's new?

Added AWS based email function. 
Users will receive email upon register and change password. 

## Content
- [Introduction](#jr-academy-projects-3---campus-system-10)
- [What's new](#whats-new)
- [About](#about)
- [Tech Stack](#tech-stack-for-backend)
- [Database](#database)
- [How to run this program locally](#how-to-run-this-program-locally)
- [Future Improvement](#future-improvement)

## About
This is project three from JR Academy. 
We are team RMR101. Objective of the project is to 
deliver a learning management system for an educational institution.

#### Backend Feature Summary
<ul>
<li>Secure log-in with encrypted password and JWT authentication</li>
<li>AWS document upload and download for handing assignments</li>
</ul>

#### Tech stack for Backend:

Framework:
- Spring Boot
- Maven

Testing:
- Swagger UI
- Jest & Jest-dom
- Testing library

Security:
- JSON Web Token

Clouds:
- AWS S3 & Presigned URL
- AWS SDK 
- AWS Simple Email Service

CI/CD:
- Java CI with Maven
- Docker/ Docker Hub

Development:
- Agile (Scrumn)
- Github flow

#### Database EER diagram
![](./demo/images/Campus_Database.jpg)

## How to run this program locally
- required programs
    - JAVA version 14 [link](https://docs.oracle.com/en/java/javase/14/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA)
    - Docker [link](https://www.docker.com/get-started)
    - IntelliJ IDEA (Recommended)

#### Spring Boot Configuration
- Git Clone or [download](https://github.com/rmr101/campus-backend/archive/master.zip) 
master code to your local drive 
- Unzip and open downloaded file in IntelliJ IDEA or your preferred editor
- Start the main application in<br> 
    src/main/java/com.rmr101.campus/CampusApplication.java


#### Database Configuration(connect to Docker Hub)
- pull pre-packed mysql image from command line (Docker login required):

    `docker pull nkanyang/mysql:admin`
    
- Start docker image:

    `docker run -itd --name mysql-campus -p 3306:3306 -e MYSQL_CAMPUS_PASSWORD=123456 nkanyang/mysql:campus-admin`

- login container

    `docker exec -it mysql-test bash`

- login mysql

    `mysql -u root -p`
    
    input password:
    
    `123456`
    
    `use campus`
    
    `select * from user;`
    
    it should display a campus_id as "admin", now we can log in as admin with password: admin 

#### Swagger UI

- Step1: Start application 

- Step2: Access URL `http://localhost:8080/swagger-ui.html` in your browser


## Future Improvement
#### Features
<ul>
<li>generate a password reset email to users upon reset password request</li>
<li>allow multiple teacher to teach one course</li>
<li>pagination in search results</li>
<li>add JWT function in SWAGGER UI for easier feature development</li>
</ul>

#### Tech:
<ul>
<li>add an long expiry JWT as log-in credential</li>
<li>deploy to AWS for easy access and stronger security</li>
<li>add load balancer </li>
<li>move from http to https</li>
</ul>
