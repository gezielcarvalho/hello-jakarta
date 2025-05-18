FROM maven:3.8-eclipse-temurin-8

# Cleaner version
WORKDIR /workspaces/hello-jakarta

# Install latest Tomcat 9.x version
RUN apt-get update && apt-get install -y curl wget && \
    TOMCAT_VERSION=$(curl -s https://downloads.apache.org/tomcat/tomcat-9/ | grep -oP '9\.\d+\.\d+/' | sort -V | tail -n 1 | tr -d '/') && \
    echo "Downloading Tomcat version: $TOMCAT_VERSION" && \
    wget https://downloads.apache.org/tomcat/tomcat-9/v$TOMCAT_VERSION/bin/apache-tomcat-$TOMCAT_VERSION.tar.gz && \
    tar xzf apache-tomcat-$TOMCAT_VERSION.tar.gz && \
    mv apache-tomcat-$TOMCAT_VERSION /opt/tomcat && \
    chmod +x /opt/tomcat/bin/*.sh

RUN apt-get update && apt-get install -y curl && \
    curl -sLO https://github.com/watchexec/watchexec/releases/download/v2.3.0/watchexec-2.3.0-x86_64-unknown-linux-gnu.tar.xz && \
    apt-get install -y xz-utils && \
    tar -xJf watchexec-2.3.0-x86_64-unknown-linux-gnu.tar.xz && \
    rm watchexec-2.3.0-x86_64-unknown-linux-gnu.tar.xz && \
    mv watchexec-*/watchexec /usr/local/bin && \
    chmod +x /usr/local/bin/watchexec

# Copy custom Tomcat configuration files from overrides/
COPY overrides/context.xml /opt/tomcat/webapps/manager/META-INF/context.xml
COPY overrides/tomcat-users.xml /opt/tomcat/conf/tomcat-users.xml
    
ENV CATALINA_HOME=/opt/tomcat
ENV PATH=$CATALINA_HOME/bin:$PATH

EXPOSE 8080
