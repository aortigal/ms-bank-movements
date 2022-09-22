package com.nttdata.msbankmovements.entity;

import com.nttdata.msbankmovements.enums.CuentaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cuentas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_id", nullable = false)
    private Integer cuentaid;

    @Column(name = "numero", nullable = false, length = 30)
    private String numero;

    @Column(name = "tipo", nullable = false, length = 12)
    private CuentaEnum tipo;

    @Column(name = "saldo_inicial", nullable = false)
    private Double saldoInicial;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey =@ForeignKey(name = "fk_cuenta_cliente"))
    private Cliente cliente;
}
