FROM maven:3.8-eclipse-temurin-8

WORKDIR /workspace
COPY . /workspace

# Install Tomcat 9.0.105 inside the dev container
RUN apt-get update && apt-get install -y wget && \
    wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.105/bin/apache-tomcat-9.0.105.tar.gz && \
    tar xzf apache-tomcat-9.0.105.tar.gz && \
    mv apache-tomcat-9.0.105 /opt/tomcat && \
    chmod +x /opt/tomcat/bin/*.sh

ENV CATALINA_HOME=/opt/tomcat
ENV PATH=$CATALINA_HOME/bin:$PATH

EXPOSE 8080