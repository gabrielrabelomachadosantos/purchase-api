
FROM maven:3.6.3-openjdk-11 as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml package -Dmaven.test.skip=true

FROM openjdk:11
COPY --from=build /home/app/target/purchase-api.jar /user/local/lib/purchase-api.jar
COPY target/purchase-api.jar user/local/lib/purchase-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/user/local/lib/purchase-api.jar"]