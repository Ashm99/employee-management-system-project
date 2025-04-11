
# Use a base image with JDK 17 (or whichever version you used)
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy the jar (you can also build in this Dockerfile if needed)
COPY target/employeemanagementsystem-0.0.1-SNAPSHOT.jar app.jar

# Copy the truststore (used for SSL connection)
COPY src/main/resources/ssl/tidb-truststore.jks tidb-truststore.jks

# Run the app with truststore
ENTRYPOINT ["java", "-Djavax.net.ssl.trustStore=tidb-truststore.jks", "-Djavax.net.ssl.trustStorePassword=changeit", "-jar", "app.jar"]
