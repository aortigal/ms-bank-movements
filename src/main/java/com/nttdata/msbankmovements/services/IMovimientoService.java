package com.nttdata.msbankmovements.services;

import com.nttdata.msbankmovements.entity.Movimiento;

import java.util.List;

public interface IMovimientoService {

    Movimiento save(Integer clienteId, Movimiento movimiento);

    Movimiento update(Integer id, Movimiento movimiento);

    List<Movimiento> getListAll();

    Movimiento getById(Integer id);

    boolean delete(Integer id);

}
