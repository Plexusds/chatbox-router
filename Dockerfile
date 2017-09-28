FROM java:8-jre
MAINTAINER dataspartan
ADD ./target/router-0.0.1-SNAPSHOT.jar /
CMD ["sh", "-c", "java -jar router-0.0.1-SNAPSHOT.jar"]