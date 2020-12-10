#pull base image
FROM openjdk:8-jdk-alpine

#maintainer 
MAINTAINER ezecruzavila@gmail.com

#expose port 1001
EXPOSE 1001

#copy jar to docker image
ADD ./target/mercadolibreChallenge.jar mercadolibreChallenge.jar

#default command
CMD java -jar mercadolibreChallenge.jar


#ENTRYPOINT ["sh", "-c", "java", "-Dspring.config.location=fhir/application.properties", "-Djava.security.egd=file:/dev/./urandom","-Dfile.encoding=es_ES.UTF-8", "-jar", "appfhir.jar"]