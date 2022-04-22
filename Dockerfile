FROM tomcat
EXPOSE 8080
COPY target/manageinspections-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh", "run"]
