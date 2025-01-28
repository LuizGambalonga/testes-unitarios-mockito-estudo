--liquibase formatted sql

--changeset luiz.serafim:create-usuario:001
CREATE SEQUENCE IF NOT EXISTS gambalonga_owner.usuario_seq START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE gambalonga_owner.usuario_seq;

--changeset luiz.serafim:create-usuario:002
CREATE TABLE IF NOT EXISTS GAMBALONGA_OWNER.usuario (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('gambalonga_owner.usuario_seq'),
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    i_database BIGINT NOT NULL,
    CONSTRAINT fk_i_database FOREIGN KEY (i_database) REFERENCES GAMBALONGA_OWNER.database (id)
);
--DROP TABLE IF EXISTS GAMBALONGA_OWNER.usuario;

--changeset luiz.serafim:create-usuario:003
CREATE OR REPLACE VIEW GAMBALONGA_OWNER.VW_usuario AS
SELECT *
FROM GAMBALONGA_OWNER.usuario
WHERE i_database = current_setting('context.i_databases'::text)::numeric;
--DROP VIEW IF EXISTS GAMBALONGA_OWNER.VW_usuario;

