FROM tomcat:8-jre8

ADD iaapi/target/iaapi.war /usr/local/tomcat/webapps/
ADD tomcat.conf.xml /usr/local/tomcat/conf/server.xml

RUN java -version
