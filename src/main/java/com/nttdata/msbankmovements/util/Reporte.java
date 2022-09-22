package com.nttdata.msbankmovements.util;

import lombok.Data;

@Data
public class Reporte {

    private String fecha;

    private String cliente;

    private String numeroCuenta;

    private String tipo;

    private Double saldoInicial;

    private boolean estado;

    private Double Movimiento;

    private Double saldoDisponible;
}
