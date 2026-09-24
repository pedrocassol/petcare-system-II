# PetCare

Sistema para gerenciamento de uma clínica veterinária, desenvolvido como projeto acadêmico para a disciplina de **Programação Orientada a Objetos para Web II**.

## Tecnologias

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Swagger / OpenAPI
* Flyway
* Bean Validation

## Funcionalidades

O sistema possui três principais recursos:

* **Proprietários**

  * Cadastro
  * Consulta
  * Atualização
  * Exclusão

* **Pets**

  * Cadastro
  * Consulta
  * Atualização
  * Exclusão
  * Associação com proprietário

* **Consultas**

  * Cadastro
  * Consulta
  * Atualização
  * Exclusão
  * Associação com pet

A API também possui:

* Validação dos dados recebidos;
* Respostas HTTP utilizando `ResponseEntity`;
* Tratamento de erros e retorno `404` para recursos não encontrados;
* Documentação dos endpoints com Swagger / OpenAPI.

## Estrutura do projeto

O projeto utiliza uma organização em camadas:

```text
src/
└── main/
    ├── java/
    │   └── com.petcare/
    │       ├── controller/
    │       ├── infra/
    │       ├── model/
    │       ├── repository/
    │       └── service/
    │
    └── resources/
        └── application.properties
```

### Camadas

* `model` — entidades utilizadas pelo sistema;
* `repository` — acesso e operações com o banco de dados;
* `service` — regras e operações dos recursos;
* `controller` — endpoints da API REST;
* `infra` — tratamento de exceções e erros da aplicação.

## API

A API disponibiliza operações CRUD para:

| Recurso       | Endpoint         |
| ------------- | ---------------- |
| Proprietários | `/proprietarios` |
| Pets          | `/pets`          |
| Consultas     | `/consultas`     |

Cada recurso possui operações para:

* `GET` — consulta;
* `POST` — cadastro;
* `PUT` — atualização;
* `DELETE` — exclusão.

## Banco de dados

O sistema utiliza **PostgreSQL** para armazenamento dos dados.

As configurações de acesso ao banco são definidas no arquivo `application.properties`.

A senha do banco é obtida através da variável de ambiente `DB_PASSWORD`, evitando que a senha seja armazenada diretamente no código-fonte.

O projeto também utiliza **Flyway** para gerenciamento das migrações do banco de dados.

## Swagger / OpenAPI

A API possui documentação dos endpoints utilizando **Swagger / OpenAPI**.

Após executar a aplicação, a documentação pode ser acessada pela interface do Swagger.

## Como executar

### Pré-requisitos

* Java
* Maven
* PostgreSQL

### Configuração do banco

Configure o banco de dados PostgreSQL e defina a variável de ambiente:

```text
DB_PASSWORD=sua_senha
```

Depois, execute a aplicação através da IDE ou utilizando o Maven.

Após a aplicação iniciar, os endpoints estarão disponíveis em:

```text
http://localhost:8080
```

## Projeto acadêmico

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos para Web II**.
