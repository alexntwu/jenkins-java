FROM tomcat:10.0.27-jdk17

LABEL maintainer="alexnt.wu@ispan.com.tw"

ENV TZ="Asia/Taipei" 

EXPOSE 8080/tcp

#宣告變數war_file並設定預設值為xxx.war
ARG war_file=xxx.war

ADD ./${war_file} /usr/local/tomcat/webapps/ROOT.war

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]
