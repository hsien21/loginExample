FROM openjdk:11          
ARG JAR_FILE=target/member-0.0.1.jar              
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8088  