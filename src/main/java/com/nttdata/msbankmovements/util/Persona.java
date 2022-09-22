package com.nttdata.msbankmovements.util;

import com.nttdata.msbankmovements.enums.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class Persona {

    @Column(name = "identificacion", nullable = false, length = 30)
    private String identificacion;

    @Column(name = "nombres", nullable = false, length = 60)
    private String nombres;

    @Column(name = "genero", nullable = false, length = 10)
    private GeneroEnum genero;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "direccion", nullable = false,  length = 200)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

}
