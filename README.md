# PetCare

API REST para gerenciamento de uma clínica veterinária, desenvolvida como projeto acadêmico para a disciplina de **Programação Orientada a Objetos para Web II**.

O sistema permite cadastrar e gerenciar proprietários, pets e consultas veterinárias, utilizando Java, Spring Boot e PostgreSQL.

## Tecnologias utilizadas

- Java 21
- Spring Boot 4.1.1
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Flyway
- Swagger / OpenAPI
- Bean Validation
- Lombok

## Funcionalidades

O sistema possui três principais recursos:

### Proprietários

- Cadastro de proprietários;
- Listagem e consulta por UUID;
- Atualização de dados;
- Exclusão;
- Validação de nome, e-mail e telefone;
- Endereço incorporado com `@Embeddable` e `@Embedded`.

### Pets

- Cadastro de pets;
- Listagem e consulta por UUID;
- Busca de pets por proprietário;
- Atualização completa com PUT;
- Atualização parcial com PATCH;
- Exclusão;
- Associação com proprietário.

### Consultas veterinárias

- Cadastro de consultas;
- Listagem e consulta por UUID;
- Atualização;
- Exclusão;
- Associação com pet.

## Recursos da API

A aplicação utiliza os seguintes recursos do Spring Boot e Spring Data JPA:

- Mapeamento de entidades com JPA e Hibernate;
- Repositórios utilizando `JpaRepository`;
- Relacionamentos `OneToMany` e `ManyToOne`;
- Identificadores UUID para acesso aos recursos pela API;
- Ocultação dos IDs numéricos nas respostas JSON;
- Consultas personalizadas utilizando `@Query`;
- DTO (Data Transfer Object) para controlar os dados retornados;
- Atualização parcial com PATCH;
- Dirty Checking para detectar alterações em entidades gerenciadas;
- Validação de dados com Bean Validation;
- Tratamento personalizado de erros HTTP;
- Respostas HTTP utilizando `ResponseEntity`;
- Migrações do banco de dados com Flyway.

## Estrutura do projeto

O projeto utiliza uma organização em camadas:

```text
src/
└── main/
    ├── java/
    │   └── com.petcare/
    │       ├── controller/
    │       ├── dto/
    │       ├── infra/
    │       ├── model/
    │       ├── repository/
    │       └── service/
    │
    └── resources/
        ├── db/
        │   └── migration/
        └── application.properties
```

### Organização das camadas

- `controller` — endpoints da API REST;
- `dto` — objetos utilizados para transferência de dados;
- `infra` — tratamento de exceções e erros da aplicação;
- `model` — entidades e objetos incorporados do sistema;
- `repository` — acesso e operações com o banco de dados;
- `service` — regras e operações dos recursos.

## Endpoints da API

Todas as rotas de identificação individual utilizam UUID.

### Proprietários

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| POST | `/proprietarios` | Cadastrar proprietário |
| GET | `/proprietarios` | Listar proprietários |
| GET | `/proprietarios/{uuid}` | Buscar proprietário |
| PUT | `/proprietarios/{uuid}` | Atualizar proprietário |
| DELETE | `/proprietarios/{uuid}` | Excluir proprietário |

### Pets

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| POST | `/pets` | Cadastrar pet |
| GET | `/pets` | Listar pets |
| GET | `/pets/{uuid}` | Buscar pet |
| GET | `/pets/proprietario/{uuid}` | Listar pets por proprietário |
| PUT | `/pets/{uuid}` | Atualizar pet |
| PATCH | `/pets/{uuid}` | Atualizar parcialmente um pet |
| DELETE | `/pets/{uuid}` | Excluir pet |

### Consultas

| Método HTTP | Endpoint | Descrição |
|---|---|---|
| POST | `/consultas` | Cadastrar consulta |
| GET | `/consultas` | Listar consultas |
| GET | `/consultas/{uuid}` | Buscar consulta |
| PUT | `/consultas/{uuid}` | Atualizar consulta |
| DELETE | `/consultas/{uuid}` | Excluir consulta |

### Códigos HTTP

A API utiliza diferentes códigos de resposta conforme o resultado das operações:

| Código | Descrição |
|---|---|
| 200 | Requisição realizada com sucesso |
| 201 | Recurso criado |
| 204 | Recurso excluído com sucesso, sem corpo de resposta |
| 400 | Dados inválidos |
| 404 | Recurso não encontrado |

## Relacionamentos entre entidades

O sistema possui dois relacionamentos bidirecionais:

- **Proprietário e Pet:** um proprietário pode possuir vários pets, enquanto cada pet pertence a um proprietário.
- **Pet e Consulta:** um pet pode possuir várias consultas, enquanto cada consulta está associada a um pet.

Os relacionamentos são implementados utilizando `@OneToMany` e `@ManyToOne`.

## Identificação dos recursos

As entidades possuem IDs numéricos gerados pelo banco de dados e UUIDs gerados pelo Hibernate.

Os UUIDs são utilizados nas rotas da API para identificar os recursos. Os IDs numéricos permanecem como chaves primárias internas e não são exibidos nas respostas JSON.

Para estabelecer relacionamentos em requisições de cadastro e atualização, o ID numérico da entidade relacionada pode ser enviado no corpo da requisição.

## DTO e atualização parcial

A aplicação utiliza objetos DTO para controlar quais informações são transmitidas pela API.

O `PetDTO` disponibiliza apenas os dados selecionados de um pet:

- UUID;
- Nome;
- Espécie;
- Raça;
- Idade.

A aplicação também possui `PetPatchDTO`, utilizado para receber os campos opcionais nas atualizações parciais de pets.

## Banco de dados

O projeto utiliza **PostgreSQL** como banco de dados relacional.

As configurações estão definidas no arquivo `application.properties`.

O acesso ao banco utiliza a variável de ambiente `DB_PASSWORD`, evitando armazenar a senha diretamente no código-fonte.

O projeto utiliza **Flyway** para criação e evolução do esquema do banco de dados por meio de migrations SQL.

O banco utilizado na configuração local é `petcare_ii`.

## Swagger / OpenAPI

A documentação da API é gerada utilizando Springdoc e OpenAPI.

Após iniciar a aplicação, a interface do Swagger pode ser acessada em:

```text
http://localhost:8080/swagger-ui/index.html
```

A interface permite visualizar os endpoints, parâmetros, modelos de dados e respostas HTTP documentadas.

## Como executar o projeto

### Pré-requisitos

- Java 21;
- PostgreSQL;
- Maven (ou Maven Wrapper incluído no projeto).

### 1. Configurar o banco de dados

Crie um banco de dados PostgreSQL chamado:

```sql
CREATE DATABASE petcare_ii;
```

### 2. Configurar a senha

Defina a variável de ambiente `DB_PASSWORD` com a senha do usuário PostgreSQL.

No PowerShell:

```powershell
$env:DB_PASSWORD="sua_senha"
```

### 3. Executar a aplicação

Na pasta raiz do projeto, execute pelo Maven Wrapper:

```powershell
.\mvnw.cmd spring-boot:run
```

Também é possível executar a aplicação diretamente pela IDE.

Após a inicialização, o Flyway executará automaticamente as migrations pendentes.

A API ficará disponível em:

```text
http://localhost:8080
```

## Projeto acadêmico

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos para Web II**, com o objetivo de aplicar conceitos de desenvolvimento de APIs REST utilizando Java, Spring Boot, JPA e PostgreSQL.