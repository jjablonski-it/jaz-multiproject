FROM openjdk:15-slim
ADD build/libs/jaz-client-0.0.1-SNAPSHOT.jar ./jaz-client.jar
EXPOSE 22222
CMD java -jar ./jaz-client.jar --envName=prod
