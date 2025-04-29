package com.example.AlimentaAcaoAPP.Entities.Enums;

public enum TipoUsuario {

    ADMIN("admin"),
    BENEFICIARIO("beneficiario");

    private final String tipoUsuario;

    TipoUsuario(String tipo) {
        this.tipoUsuario = tipo;
    }
}