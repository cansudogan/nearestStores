# nearestStores

#### Overview
Store servis has two endpoints:
1. [GET] /api/stores finds nearest stores. As a default, it gets 5 stores but it is pageable. So the page count can changed according to need.
2. [GET] /api/search/stores also finds nearest stores. But the result can be filtered by collection point and only open stores.

------------


|  Technologies | |
| ------------ | ------------ |
|  Java | 18 |
|  SpringBoot | 2.7.0 |
| Maven |6.8.3 |
| MongoDB   | 5.0.4 |
| TestContainers-MongoDB   | 1.17.2 |
|  Swagger-UI  | 3.0.0|

------------

#### Run project
    mvn package
    docker-compose-up --build

------------

#### Swagger
http://localhost:8080/swagger-ui.html#/

