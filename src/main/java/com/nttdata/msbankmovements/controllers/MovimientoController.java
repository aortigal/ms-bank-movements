package com.nttdata.msbankmovements.controllers;

import com.nttdata.msbankmovements.entity.Movimiento;
import com.nttdata.msbankmovements.services.IMovimientoService;
import com.nttdata.msbankmovements.util.Reporte;
import com.nttdata.msbankmovements.util.Transaction;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.ws.rs.QueryParam;
import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
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

    @PostMapping("/{numeroCuenta}")
    @CircuitBreaker(name="cuenta", fallbackMethod = "fallBackCuenta")
    public ResponseEntity<Object> save(@PathVariable("numeroCuenta") String numeroCuenta, @RequestBody Transaction transaction){
        log.info("[INI] save Movimiento");

        ResponseEntity<Object> p = movimientoService.save(numeroCuenta, transaction);

        log.info("[END] save Movimiento");
        return p;
    }

    @PutMapping("/{id}")
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

    @GetMapping("/reportes/{clienteId}")
    @CircuitBreaker(name="cuenta", fallbackMethod = "fallBackCuenta")
    public ResponseEntity<Object> reporteByFechaAndUsuario(@PathVariable("clienteId") Integer clienteId
            , @RequestParam("fechaInicio") String fechaInicio
            , @RequestParam("fechaFin") String fechafin){
        log.info("[INI] reporteByFechaAndUsuario Movimiento " + clienteId + " , fechaInicio= "+ fechaInicio + " , fechaFin= "+fechafin);

        List<Reporte> reportes = movimientoService.reporteByFechaAndUsuario(clienteId, fechaInicio, fechafin);

        log.info("[END] reporteByFechaAndUsuario Movimiento " + clienteId + " , fechaInicio= "+ fechaInicio + " , fechaFin= "+fechafin);
        return new ResponseEntity<Object>(reportes, HttpStatus.OK);
    }

    public ResponseEntity<Object> fallBackCuenta(RuntimeException runtimeException){
        return new ResponseEntity<Object>("Microservicio externo no responde. /n error: "+runtimeException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
