package com.nttdata.msbankmovements.controllers;

import com.nttdata.msbankmovements.entity.Movimiento;
import com.nttdata.msbankmovements.services.IMovimientoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private IMovimientoService movimientoService;

    private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

    @GetMapping
    public ResponseEntity<List<Movimiento>> getListALl(){
        log.info("[INI] getListALl Movimiento");

        List<Movimiento> lista = movimientoService.getListAll();

        log.info("[END] getListALl Movimiento");
        return new ResponseEntity<List<Movimiento>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getListById(@PathVariable("id") Integer id){
        log.info("[INI] getListById Movimiento " + id);

        Movimiento movimiento = movimientoService.getById(id);

        log.info("[END] getListById Movimiento " + id);
        return new ResponseEntity<Movimiento>(movimiento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movimiento> save(@RequestBody Movimiento movimiento){
        log.info("[INI] save Movimiento");

        Movimiento p = movimientoService.save(movimiento);

        log.info("[END] save Movimiento");
        return new ResponseEntity<Movimiento>(p, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody Movimiento movimiento){
        log.info("[INI] update Movimiento " + id);

        Movimiento p = movimientoService.update(id, movimiento);

        log.info("[END] update Movimiento " + id);

        if(p!=null){
            return new ResponseEntity<Object>(p, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<Object>("Movimiento no Existe.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
        log.info("[INI] delete Movimiento " + id);

        movimientoService.delete(id);

        log.info("[END] delete Movimiento " + id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
