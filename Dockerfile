FROM tomcat:9.0-jdk8
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/hello-jakarta /usr/local/tomcat/webapps/hello-jakarta
EXPOSE 8080
CMD ["catalina.sh", "run"]
