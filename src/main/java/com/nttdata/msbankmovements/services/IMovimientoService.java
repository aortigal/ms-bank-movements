package com.nttdata.msbankmovements.services;

import com.nttdata.msbankmovements.entity.Movimiento;
import com.nttdata.msbankmovements.util.Reporte;
import com.nttdata.msbankmovements.util.Transaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IMovimientoService {

    ResponseEntity<Object> save(String numeroCuenta, Transaction transaction);

    Movimiento update(Integer id, Movimiento movimiento);

    List<Movimiento> getListAll();

    Movimiento getById(Integer id);

    boolean delete(Integer id);

    List<Reporte> reporteByFechaAndUsuario(Integer clienteId, String fechaInicio, String fechafin);
}
