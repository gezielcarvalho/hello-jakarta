FROM maven:3.8-eclipse-temurin-8

WORKDIR /workspace
COPY . /workspace

# Install Tomcat 9.0.105 inside the dev container
RUN apt-get update && apt-get install -y wget && \
    wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.105/bin/apache-tomcat-9.0.105.tar.gz && \
    tar xzf apache-tomcat-9.0.105.tar.gz && \
    mv apache-tomcat-9.0.105 /opt/tomcat && \
    chmod +x /opt/tomcat/bin/*.sh 

RUN apt-get update && apt-get install -y curl && \
    curl -sLO https://github.com/watchexec/watchexec/releases/download/v2.3.0/watchexec-2.3.0-x86_64-unknown-linux-gnu.tar.xz && \
    apt-get install -y xz-utils && \
    tar -xJf watchexec-2.3.0-x86_64-unknown-linux-gnu.tar.xz && \
    rm watchexec-2.3.0-x86_64-unknown-linux-gnu.tar.xz && \
    mv watchexec-*/watchexec /usr/local/bin && \
    chmod +x /usr/local/bin/watchexec
    
ENV CATALINA_HOME=/opt/tomcat
ENV PATH=$CATALINA_HOME/bin:$PATH

EXPOSE 8080
