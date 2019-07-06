FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=moviedb
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root123
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/moviedb
ADD ./target/MovieCruiserService-1.0-SNAPSHOT.jar /usr/src/MovieCruiserService-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","MovieCruiserService-1.0-SNAPSHOT.jar"]
