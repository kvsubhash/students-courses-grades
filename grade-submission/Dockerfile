FROM eclipse-temurin:17.0.8.1_1-jdk-ubi9-minimal

WORKDIR /app

COPY .mvn/ .mvn
COPY ["mvnw", "mvnw.cmd", "pom.xml", "./"]
COPY ["src", "./src"]

RUN ./mvnw dependency:resolve

EXPOSE 8081

CMD [ "./mvnw", "spring-boot:run" ]