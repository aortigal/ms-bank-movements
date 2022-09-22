package com.nttdata.msbankmovements.repository;

import com.nttdata.msbankmovements.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovimientoRepository extends JpaRepository<Movimiento, Integer> {
}
