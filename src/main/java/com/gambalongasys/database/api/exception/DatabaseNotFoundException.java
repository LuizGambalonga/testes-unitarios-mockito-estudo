package com.gambalongasys.database.api.exception;

public class DatabaseNotFoundException extends RuntimeException {
    public DatabaseNotFoundException(String mensagem) {
        super(mensagem);
    }
}
