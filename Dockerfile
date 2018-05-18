FROM mherwig/alpine-java-mongo

ADD . .

RUN ./gradlew build

ENTRYPOINT mongod --fork --logpath /var/log/mongodb.log && java $JAVA_OPTS -jar build/libs/microservicespike-0.0.1-SNAPSHOT.jar
