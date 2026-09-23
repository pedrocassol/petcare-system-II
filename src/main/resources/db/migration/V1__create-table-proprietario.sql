CREATE TABLE proprietario(
    id SERIAL PRIMARY KEY,
    uuid UUID NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco VARCHAR(255)
);