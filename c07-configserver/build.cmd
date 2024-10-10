mvn install
mvn package
docker build -t c07-configserver . c07-configserver-0.0.1-SNAPSHOT.jar
docker run c07-configserver