# arm 아키텍쳐 기반 오라클 클라우드에 배포하는 도커파일
# 사전에 Gradle 빌드를 해야함.
# ./gradlew build -x test
# 이후 Docker 빌드 시 파일을 지정해줌.
# docker buildx build --platform linux/arm64 . -f arm.Dockerfile
FROM openjdk:8-jdk-alpine

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

ARG ENVIRONMENT=prod
ENV SPRING_PROFILES_ACTIVE=${ENVIRONMENT}

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
