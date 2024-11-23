# Start with a base image containing Java Runtime
FROM amazoncorretto:21

EXPOSE 8080

# Add Maintainer Info
LABEL maintainer="Tutku Ince <incetutku@gmail.com>"

# The application's jar file - defines the JAR_FILE variable set by dockerfile-maven-plugin
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Execute the application
ENTRYPOINT exec java \
    -Dspring.profiles.active=$SPRING_PROLE \
    -jar app.jar