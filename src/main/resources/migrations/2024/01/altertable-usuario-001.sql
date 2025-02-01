--liquibase formatted sql

--changeset luiz.serafim:altertable-usuario:001
ALTER TABLE GAMBALONGA_OWNER.USUARIO DROP CONSTRAINT usuario_email_key;
--rollback ALTER TABLE GAMBALONGA_OWNER.USUARIO ADD CONSTRAINT usuario_email_key UNIQUE (email);

--changeset luiz.serafim:altertable-usuario:002
CREATE OR REPLACE VIEW GAMBALONGA_OWNER.VW_usuario AS
SELECT *
FROM GAMBALONGA_OWNER.usuario
WHERE i_database = current_setting('context.i_databases'::text)::numeric;
--DROP VIEW IF EXISTS GAMBALONGA_OWNER.VW_usuario;

--changeset luiz.serafim:altertable-usuario:003
ALTER TABLE GAMBALONGA_OWNER.usuario ADD CONSTRAINT uq_usuario_email_database UNIQUE (email, i_database);
--rollback ALTER TABLE GAMBALONGA_OWNER.usuario DROP CONSTRAINT uq_usuario_email_database;