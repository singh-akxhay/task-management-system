FROM azul/zulu-openjdk:17

WORKDIR /app

COPY build/libs/task-management-system-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "task-management-system-0.0.1-SNAPSHOT.jar"]
