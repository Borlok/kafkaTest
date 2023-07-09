FROM openjdk
ARG JAR_PATH=./target/*.jar
ARG WORK_DIR=/usr/app
EXPOSE 8080
COPY ${JAR_PATH} ${WORK_DIR}/app.jar
WORKDIR $WORK_DIR
ENTRYPOINT ["java", "-jar", "app.jar"]
