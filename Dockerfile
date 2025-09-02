FROM openjdk:21-jdk
ADD build/libs/myapp.jar /
ENTRYPOINT ["java", "-jar", "myapp.jar"]