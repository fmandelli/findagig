FROM amazoncorretto:11
COPY target/findagig-batch-1.0.jar findagig-batch-1.0.jar
CMD java -jar -Dspring.profiles.active=local findagig-batch-1.0.jar
EXPOSE 8080