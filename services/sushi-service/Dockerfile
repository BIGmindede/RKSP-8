FROM amazoncorretto:21.0.5-alpine
WORKDIR /app
COPY /build/libs/sushi-service.jar /app
CMD ["java", "-jar", "sushi-service.jar"]
EXPOSE 8081