package com.nttdata.msbankmovements.util;

import com.nttdata.msbankmovements.enums.MovimientoEnum;
import lombok.Data;

@Data
public class Transaction {

    private MovimientoEnum tipo;
    private Double movimiento;
}
