FROM openjdk:8
ADD restfullapi.jar restfullapi.jar
RUN bash -c 'touch /restfullapi.jar'
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-Dspring.profiles.active=container","-jar", "restfullapi.jar"]