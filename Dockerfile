FROM openjdk:8-alpine
COPY target/store-managment.jar store-managment.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","store-managment.jar"]
