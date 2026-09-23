# PetCare

Sistema para gerenciamento de uma clínica veterinária, desenvolvido como projeto acadêmico.

## Tecnologias

- Java
- Spring Boot
- PostgreSQL
- Maven
- Swagger / OpenAPI

## Funcionalidades

- Cadastro de proprietários
- Cadastro de pets
- Cadastro de consultas
- Operações CRUD
- Relacionamento entre proprietários, pets e consultas
- Documentação da API com Swagger / OpenAPI

## Estrutura do projeto

O projeto utiliza uma estrutura organizada em camadas:

- `model` — entidades do sistema
- `repository` — acesso aos dados
- `service` — regras e operações do sistema
- `controller` — endpoints da API REST

## API

A API possui operações CRUD para:

- Proprietários
- Pets
- Consultas

A documentação dos endpoints pode ser acessada através do Swagger / OpenAPI.

## Banco de dados

O sistema utiliza PostgreSQL como banco de dados e Flyway para o gerenciamento das migrações.

## Projeto

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos para Web II.