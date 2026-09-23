CREATE TABLE consulta(
        id SERIAL PRIMARY KEY,
        uuid UUID NOT NULL UNIQUE,
        id_pet BIGINT NOT NULL,
        data_hora TIMESTAMP NOT NULL,
        veterinario VARCHAR(100) NOT NULL,
        descricao VARCHAR(255),
        valor_estimado DECIMAL(10,2),
        status VARCHAR(30) NOT NULL,
        observacoes VARCHAR(255),

        CONSTRAINT fk_consulta_pet
            FOREIGN KEY (id_pet)
                REFERENCES pet(id)
);