--liquibase formatted sql

--changeset luiz.serafim:create-database:001
CREATE SEQUENCE IF NOT EXISTS gambalonga_owner.database_seq START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE gambalonga_owner.database_seq;

--changeset luiz.serafim:create-database:002
CREATE TABLE IF NOT EXISTS GAMBALONGA_OWNER.database (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('gambalonga_owner.database_seq'),
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14),
    cnpj VARCHAR(18),
    email VARCHAR(255) NOT NULL
    CONSTRAINT cpf_check CHECK (LENGTH(cpf) = 14),
    CONSTRAINT cnpj_check CHECK (LENGTH(cnpj) = 18)
);
--DROP TABLE IF EXISTS GAMBALONGA_OWNER.databases;