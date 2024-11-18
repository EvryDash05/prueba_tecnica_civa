package com.example.back_prueba_tecnica_civa.commons.enums;

import lombok.Getter;

@Getter
public enum BusStatus {
    ACTIVE("Activo"),
    INACTIVE("Inactivo");

    private final String value;

    BusStatus(String value) {
        this.value = value;
    }
}
