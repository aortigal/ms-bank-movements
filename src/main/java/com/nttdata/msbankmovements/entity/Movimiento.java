package com.nttdata.msbankmovements.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nttdata.msbankmovements.enums.MovimientoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movimiento_id", nullable = false)
    private Integer movimientoId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "tipo", nullable = false, length = 30)
    private MovimientoEnum tipo;

    @Column(name = "movimiento", nullable = false)
    private Double movimiento;

    @Column(name = "saldo_disponible", nullable = false)
    private Double saldoDisponible;
}
