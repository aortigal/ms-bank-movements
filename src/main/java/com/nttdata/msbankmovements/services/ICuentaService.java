package com.nttdata.msbankmovements.services;

import com.nttdata.msbankmovements.entity.Cuenta;
import com.nttdata.msbankmovements.entity.Movimiento;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICuentaService {

    ResponseEntity<Cuenta> getFindById(Integer id);

    ResponseEntity<Cuenta> fingByNumeroCuenta(String numeroCuenta);

    ResponseEntity<Object> update(Integer id, Cuenta cuenta);

    ResponseEntity<List<Cuenta>> fingByCliente(Integer clienteId);
}
