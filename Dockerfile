FROM openjdk:8
COPY target/*.jar store-managment-0.0.1-SNAPSHOT.jar
#EXPOSE 9191
ENTRYPOINT ["java","-jar","store-managment-0.0.1-SNAPSHOT.jar"]
