# Asynchronous file upload API

REST API to perform asynchronous file uploads. Developed using java 8, it supports file upload upto 100 MB(Default size set to 100MB with provision to alter this value in application.properties). The API doesnt require a dedicated servlet container since it is built over spring-boot with an embedded tomcat. So just boot up the application as illustrated in usage information and start performing file upload requests using any REST client such as chrome POSTMAN

Usage:
java -jar asynchronous-file-upload-1.0.jar
