FROM openjdk:18.0
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} nearestStores-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/nearestStores-0.0.1-SNAPSHOT.jar"]