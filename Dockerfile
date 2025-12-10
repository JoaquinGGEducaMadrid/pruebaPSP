# Etapa de compilación
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copiamos el código fuente y la librería
COPY src /app/src
COPY lib /app/lib

# Compilamos los .java incluyendo el jar de nanohttpd en el classpath
RUN mkdir -p /app/bin && \
    javac -cp "/app/lib/nanohttpd-2.3.1.jar" -d /app/bin $(find /app/src -name "*.java") && \
    echo "Main-Class: Principal.Main" > manifest.txt && \
    jar cfm app.jar manifest.txt -C /app/bin .

# Etapa de ejecución
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copiamos el jar de la aplicación y la librería
COPY --from=build /app/app.jar app.jar
COPY lib /app/lib

# Ejecutamos la aplicación con el jar de nanohttpd en el classpath
CMD ["java", "-cp", "app.jar:lib/nanohttpd-2.3.1.jar", "Principal.Main"]

