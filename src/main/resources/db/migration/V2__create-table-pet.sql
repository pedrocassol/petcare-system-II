CREATE TABLE pet(
    id SERIAL PRIMARY KEY,
    uuid UUID NOT NULL UNIQUE,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raca VARCHAR(100),
    idade INTEGER,
    sexo VARCHAR(20),
    observacoes VARCHAR(255),
    id_proprietario BIGINT NOT NULL,
    CONSTRAINT fk_pet_proprietario
        FOREIGN KEY (id_proprietario)
            REFERENCES proprietario(id)
);