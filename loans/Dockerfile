#Start with a base image containing Java runtime
FROM eclipse-temurin:21-jdk

# MAINTAINER instruction is deprecated in favor of using label
# MAINTAINER Diptendu
#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="Diptendu"

# Add the application's jar to the image
COPY target/loans-0.0.1-SNAPSHOT.jar loans-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "loans-0.0.1-SNAPSHOT.jar"]