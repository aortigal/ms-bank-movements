package com.nttdata.msbankmovements.services.impl;

import com.nttdata.msbankmovements.entity.Movimiento;
import com.nttdata.msbankmovements.repository.IMovimientoRepository;
import com.nttdata.msbankmovements.services.IMovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

    @Autowired
    private IMovimientoRepository movimientoRepository;

    private static final Logger log = LoggerFactory.getLogger(MovimientoServiceImpl.class);


    @Override
    public Movimiento save(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    @Override
    public Movimiento update(Integer id, Movimiento movimiento) {
        log.info("findById "+ id);
        Optional<Movimiento> c = movimientoRepository.findById(id);
        if (c.isPresent()){
            log.info("update "+ id);
            return movimientoRepository.save(movimiento);
        }
        log.info("Movimiento no existe "+ id);
        return null;
    }

    @Override
    public List<Movimiento> getListAll() {
        return movimientoRepository.findAll();
    }

    @Override
    public Movimiento getById(Integer id) {
        Optional<Movimiento> movimiento = movimientoRepository.findById(id);
        return movimiento.orElseGet(Movimiento::new);
    }

    @Override
    public boolean delete(Integer id) {
        movimientoRepository.deleteById(id);
        return true;
    }
}
