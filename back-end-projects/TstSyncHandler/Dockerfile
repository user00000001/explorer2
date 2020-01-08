################################
### Builder image
################################
FROM maven:3.6.1-slim as builder

ENV VERSION=version2.0 \
    MYSQL_TEST_PASSWORD=qwerty

RUN apt update && \
    apt install -y \
    git

RUN git clone https://github.com/TesraSupernet/tesra-explorer.git && \
    cd tesra-explorer && \
    git checkout $VERSION

WORKDIR /tesra-explorer

RUN cd back-end-projects/TstSyncHandler && \
    mvn clean package

#################################
#### Runner image
#################################
FROM openjdk:8-jdk-alpine

ENV MYSQL_TEST_PASSWORD=qwerty

RUN apk add --no-cache ca-certificates bash

RUN addgroup tesra && \
    adduser -G tesra -s /bin/sh -D tesra -h /home/tesra

COPY --from=builder /tesra-explorer/back-end-projects/TstSyncHandler/target/tstsynchandler-0.0.1-SNAPSHOT.jar /home/tesra/app.jar
COPY --from=builder /tesra-explorer/back-end-projects/TstSyncHandler/config /home/tesra/config

# # Run as non root
USER tesra
WORKDIR /home/tesra

EXPOSE 8090

CMD ["java", "-jar", "app.jar"]