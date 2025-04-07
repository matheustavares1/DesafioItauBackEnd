# Desafio Itaú Back-End

Este projeto foi desenvolvido como parte de um desafio técnico proposto pelo Itaú, com o objetivo de criar uma API RESTful para gerenciamento de transações financeiras de clientes.

## 🚀 Tecnologias Utilizadas

- **Java 17**: Linguagem de programação principal.
- **Spring Boot**: Framework para facilitar a configuração e o desenvolvimento da aplicação.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **Docker**: Utilizado para containerização da aplicação.
- **Swagger**: Utilizado para documentação.


## 🛠️ Configuração e Execução

### Pré-requisitos

- **Java 17** ou superior instalado.
- **Maven** instalado.
- **Docker** instalado (opcional, para execução via container).

### Passos para Execução

1. **Clonar o repositório**:

   ```
   git clone https://github.com/matheustavares1/DesafioItauBackEnd.git
   ```
2. **Construir a imagem Docker**
    ```
     docker build -t desafio-itau-backend .
    ```
3. **Executar o container**
   ```
    docker run -d -p 8080:8080 --name desafio-itau-backend desafio-itau-backend
   ```

## 📝 Documentação da API
A documentação dos endpoints da API está disponível através do Swagger. Após iniciar a aplicação, acesse:
   ```
    http://localhost:8080/swagger-ui/index.html

   ```
   


