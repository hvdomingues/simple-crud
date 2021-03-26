# Simple Crud

Esse é um projeto de uma API CRUD básica de usuário, focada em boas práticas de código.<br/>
O código é todo feito em Java 11 e utiliza SpringBoot, SwaggerUI e H2 Database. 

## Instalação

Utilize o código abaixo para clonar o projeto na pasta desejada.

```bash
git clone https://github.com/hvdomingues/simple-crud.git
```

## Inicializando a aplicação

Após abrir no Eclipse ou na IDE de sua escolha, dentro da pasta "src/main/java" há o pacote principal, nomeado "com.hvdomingues.simpleCrud". Basta executar a classe "SimpleCrudApplication.java".

## Testando a aplicação
O servidor rodará por padrão na url "localhost:8080".<br/>
Para testes utilizando o Swagger, basta acessar a url "localhost:8080/swagger-ui.html".<br/>
Para ter acesso ao H2, acessar a url "http://localhost:8080/h2-console" e utilizar o usuário "sa" com a senha "password".<br/>
Na pasta Resources, há um data.sql contendo algumas inserções para teste.


# Endpoints
## "/api/user"

Método GET - Retorna um JSON com todos os usuários ativos cadastrados, parâmetros opcionais {page, size}.<br/>
Método PUT - Requer um JSON com os campos da classe UserDto, utilizando o login como identificador. Retorna um JSON contendo UserDto do usuário atualizado.<br/>
Método DELETE - Requer um JSON contendo uma String usuário e realiza o Soft Delete. Retorna um JSON contendo UserDto do usuário deletado.<br/>
Método POST - Requer um JSON com os campos da classe UserDto, insere apenas se todos estiverem devidamente preenchidos. Retorna um JSON contendo o UserDto do usuário criado.

## "/api/user/filter"

Método POST -  Requer um JSON com os campos da classe UserDto, retorna um JSON de todos os usuários ativos que atendem aos campos. Parâmetros opcionais {page, size}.

## "/api/user/deleted"

Método GET - Retorna um JSON com todos os usuários deletados cadastrados, parâmetros opcionais {page, size}.

## "api/user/login"

Método PUT - Requer dois parâmetros, sendo eles login e newLogin, referentes ao login do usuário que deseja alterar e o novo login, respectivamente. Retorna um JSON contendo o UserDto do usuário atualizado.

