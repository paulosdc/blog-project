# API - Backend

Projeto maven com a versão 3.2.2 do Spring Boot e Java 17. Dependencias: Spring Boot DevTools, Spring Web, Spring Data JPA, PostgreSQL Driver, Lombok e Spring Security. JWT usado para autenticação.

## Rodando o projeto

O projeto roda na porta 8080: `http://localhost:8080/`. Banco PostgresSQL: host 127.0.0.1 (localhost), porta 5432, nome blog_project, username 'postgres', senha 'root'. Para mudar a conexão, basta editar o arquivo application.properties.

## Principais endpoints

1) registrar um usuário:
URL: localhost:8080/auth/register
Verbo: POST
Exemplo de JSON:
{
  "name": "Paulo",
  "username": "paulo",
  "password": "123",
  "email": "paulo@gmail.com"
}

2) logar:
URL: localhost:8080/auth/login
Verbo: POST
Exemplo de JSON:
{
  "email": "paulo@gmail.com",
  "password": "123"
}
OBSERVAÇÃO: a resposta para essa requisição é um token, que deve ser enviado no header das próximas requisições como Bearer token.

3) listar os posts e álbuns:
URL: localhost:8080/posts
Verbo: GET
Observação: lembrar de incluir o Bearer token no cabeçalho da requisição.

4) criar um post:
URL: localhost:8080/posts
Verbo: POST
{
  "creator": 1,
  "content": {
    "text": "Olá",
    "images": []
  },
  "comments": []
}
Observação: lembrar de incluir o Bearer token no cabeçalho da requisição.

5) criar um álbum:
URL: localhost:8080/posts
Verbo: POST
{
  "creator": 1,
  "content": {
    "text": "Olá",
    "images": [COLOCAR AS IMAGENS SEPARADAS POR VÍRGULA. FORMATO BLOB]
  },
  "comments": []
}
Observação: lembrar de incluir o Bearer token no cabeçalho da requisição.
