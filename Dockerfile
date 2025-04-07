FROM openjdk:17-jdk-alpine


#Diretorio para trabalhar no container
WORKDIR /app

#Copiar o .jar da aplicacao para o container
COPY target/DesafioBackEndItau-0.0.1-SNAPSHOT.jar app.jar

#Porta da aplicacao
EXPOSE 8080

#Comando para rodar aplicacao
CMD ["java", "-jar", "app.jar"]

LABEL authors="Matheus Tavares"


