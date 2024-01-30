# API - Backend

Este é um projeto Maven utilizando a versão 3.2.2 do Spring Boot em conjunto com Java 17. As dependências incluem Spring Boot DevTools, Spring Web, Spring Data JPA, PostgreSQL Driver, Lombok e Spring Security. JWT é empregado para autenticação.

## Executando o Projeto

O projeto é executado na porta 8080: `http://localhost:8080/`. O banco de dados PostgreSQL está configurado com host 127.0.0.1 (localhost), porta 5432, nome "blog_project", usuário 'postgres' e senha 'root'. Para modificar a conexão, basta editar o arquivo `application.properties`.

## Principais Endpoints

1) Registrar um usuário:
   - **URL:** localhost:8080/auth/register
   - **Verbo:** POST
   - **Exemplo de JSON:**
     ```json
     {
       "name": "Paulo",
       "username": "paulo",
       "password": "123",
       "email": "paulo@gmail.com"
     }
     ```

2) Logar:
   - **URL:** localhost:8080/auth/login
   - **Verbo:** POST
   - **Exemplo de JSON:**
     ```json
     {
       "email": "paulo@gmail.com",
       "password": "123"
     }
     ```
   - **Observação:** A resposta para esta requisição é um token, que deve ser enviado no cabeçalho das próximas requisições como Bearer token.

3) Listar os posts e álbuns:
   - **URL:** localhost:8080/posts
   - **Verbo:** GET
   - **Observação:** Lembre-se de incluir o Bearer token no cabeçalho da requisição.

4) Criar um post:
   - **URL:** localhost:8080/posts
   - **Verbo:** POST
   - **Exemplo de JSON:**
     ```json
     {
       "creator": 1,
       "content": {
         "text": "Olá",
         "images": []
       },
       "comments": []
     }
     ```
   - **Observação:** Lembre-se de incluir o Bearer token no cabeçalho da requisição.

5) Criar um álbum:
   - **URL:** localhost:8080/posts
   - **Verbo:** POST
   - **Exemplo de JSON:**
     ```json
     {
       "creator": 1,
       "content": {
         "text": "Olá",
         "images": [COLOCAR AS IMAGENS SEPARADAS POR VÍRGULA. FORMATO BLOB]
       },
       "comments": []
     }
     ```
   - **Observação:** Lembre-se de incluir o Bearer token no cabeçalho da requisição.
