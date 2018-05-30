FROM anapsix/alpine-java
COPY build/libs/checkmarx-trainer-java-fat.jar checkmarx-java-trainer.jar
CMD java -jar checkmarx-java-trainer.jar
EXPOSE 4567