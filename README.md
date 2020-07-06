# reven-store
1. Project Requirements
	- Java SDK 1.8
	- Maven 3.3.5
	- Git repository: https://github.com/xonmai/reven-store

2. Instructions
	- Make sure your PC port 8080 is free
	- Compile: mvn clean install
	- Run: mvn spring-boot:run


3. API curl
3.1 GET Product: 
curl -X GET \
  http://localhost:8080/api/product/1

3.2 POST filter products: 
curl -X POST \
  http://localhost:8080/api/product/filter \
  -H 'Content-Type: application/json' \
  -d '{
	"name": "",
	"color": "Black",
	"brand": ""
}'

3.3 POST create orders
curl -X POST \
  http://localhost:8080/api/order/create \
  -H 'Content-Type: application/json' \
  -d '{
    "id": null,
    "orderDetail": [{
        "id": 1,
        "name": "iPhone Pro Max",
        "description": "Latest apple design",
        "brand": "Apple",
        "color": "Black",
        "quantity": 1
    }]
}'

3.4 GET created Order
curl -X GET \
  http://localhost:8080/api/order/2

4. Application structure
4.1 package com.reven.onlinestore.product
	Product micro-service: Everything relates to Product management will be implemented in this folder. In reality, I will be deployed in a separated container.

4.2 package com.reven.onlinestore.order
	Order micro-service: Everything relates to Order management will be implemented in this folder. In reality, I will be deployed in a separated container.

4.3 package com.reven.onlinestore.common
	Common components: Everything common or sharable micro-services can be implemented here. Will be deployed as a dependency and inject to other modules.

4.4 Other libraries used:
	- Lombok: To reduce a number of boilerplate code in Java
	- H2 Database: Embedded database, quick to install and implement this project.
	- Active-mq: Messaging queue server for communicating between micro-services

5. Software Priciples & Patterns
	- Micro-services SAGA pattern: Design a communication between services to ensure data consistency
	- Open-close principle: Apply for OrderProcessor implementation, open for extension and close for modification.
	- AOP: For auditing, just put @ActivityTrace annotation on methods which need to record activity.
	- CQRS: To separate Read & Write product functions due to huge difference in volume of requests.
	- Event Sourcing: To ensure atomicity for Order because there are a lot of events triggered.
	- Distributed Lock: Need to implement for thread-safe handling.
