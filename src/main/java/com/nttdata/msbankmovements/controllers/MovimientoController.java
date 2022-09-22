package com.nttdata.msbankmovements.controllers;

import com.nttdata.msbankmovements.entity.Movimiento;
import com.nttdata.msbankmovements.services.IMovimientoService;
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

    @GetMapping
    public ResponseEntity<List<Movimiento>> getListALl(){
        List<Movimiento> lista = movimientoService.getListAll();
        return new ResponseEntity<List<Movimiento>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getListById(@PathVariable("id") Integer id){
        Movimiento movimiento = movimientoService.getById(id);
        return new ResponseEntity<Movimiento>(movimiento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movimiento> save(@RequestBody Movimiento movimiento){
        Movimiento p = movimientoService.save(movimiento);
        return new ResponseEntity<Movimiento>(p, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Movimiento> update(@RequestBody Movimiento movimiento){
        Movimiento p = movimientoService.update(movimiento);
        return new ResponseEntity<Movimiento>(p, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
        movimientoService.delete(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }
}
