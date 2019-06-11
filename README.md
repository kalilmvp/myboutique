# My Boutique - Spring Cloud Kubernetes and OCP

_Start the Config Server_

`
docker run --name boutique-config-server -d -p 8888:8888 username/config-server:0.0.1-SNAPSHOT
`

_Start the Eureka Server_

`docker run --name boutique-eureka-server \
--link boutique-config-server \
-d -e SPRING_CLOUD_CONFIG_URI=http://boutique-config-server:8888 \
-p 8761:8761 username/boutique-eureka-server:0.0.1-SNAPSHOT`

_Start the API Gateway_

`docker run --name boutique-api-gateway \
--link boutique-config-server \
--link boutique-eureka-server -d \
-e SPRING_CLOUD_CONFIG_URI=http://boutique-config-server:8888 \
-e SERVICE_URL_DEFAULT_ZONE=http://boutique-eureka-server:8761/eureka \
-p 8340:8340 username/api-gateway:0.0.1-SNAPSHOT`

_Start the Postgres DATABASE_

`docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=demo -d postgres:10.5-alpine`

_Start ZipKin Tracer_

`docker run --name zipkin -d -p 9411:9411 openzipkin/zipkin`

_Start The Product Microservice_

`docker run --name boutique-product-service \
--link boutique-config-server \
--link boutique-eureka-server \
--link zipkin \
--link postgres -d \
-e SPRING_CLOUD_CONFIG_URI=http://boutique-config-server:8888 \
-e SERVICE_URL_DEFAULT_ZONE=http://boutique-eureka-server:8761/eureka \
-e SPRING_ZIPKIN_BASE-URL=http://zipkin:9411 \
-e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/demo \
-p 9994:9994 username/product-service:0.0.1-SNAPSHOT`

......
