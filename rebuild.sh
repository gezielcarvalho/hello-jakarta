#!/bin/bash

mvn compile war:exploded
touch /opt/tomcat/webapps/hello-jakarta/WEB-INF/web.xml
