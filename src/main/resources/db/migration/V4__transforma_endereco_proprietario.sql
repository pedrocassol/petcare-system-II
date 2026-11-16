ALTER TABLE proprietario
    ADD COLUMN rua VARCHAR(255),
    ADD COLUMN numero VARCHAR(255),
    ADD COLUMN complemento VARCHAR(255),
    ADD COLUMN bairro VARCHAR(255),
    ADD COLUMN cep VARCHAR(255),
    ADD COLUMN cidade VARCHAR(255),
    ADD COLUMN uf VARCHAR(255);


UPDATE proprietario
SET
    rua = NULLIF(TRIM(SPLIT_PART(endereco, ',', 1)), ''),
    numero = NULLIF(TRIM(SPLIT_PART(endereco, ',', 2)), '');


ALTER TABLE proprietario
DROP COLUMN endereco;