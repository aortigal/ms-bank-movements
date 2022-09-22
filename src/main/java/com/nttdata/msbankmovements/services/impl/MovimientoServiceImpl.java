package com.nttdata.msbankmovements.services.impl;

import com.nttdata.msbankmovements.entity.Cuenta;
import com.nttdata.msbankmovements.entity.Movimiento;
import com.nttdata.msbankmovements.repository.IMovimientoRepository;
import com.nttdata.msbankmovements.services.ICuentaService;
import com.nttdata.msbankmovements.services.IMovimientoService;
import com.nttdata.msbankmovements.util.Reporte;
import com.nttdata.msbankmovements.util.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovimientoServiceImpl implements IMovimientoService {

    @Autowired
    private IMovimientoRepository movimientoRepository;

    @Autowired
    private ICuentaService cuentaService;

    private static final Logger log = LoggerFactory.getLogger(MovimientoServiceImpl.class);


    @Override
    public ResponseEntity<Object> save(String numeroCuenta, Transaction transaction) {
        ResponseEntity<Cuenta> c= cuentaService.fingByNumeroCuenta(numeroCuenta);
        if(c.getStatusCode().is2xxSuccessful()){
            Cuenta cuenta = c.getBody();
            Double saldoDisponible = 0.0;
            saldoDisponible = cuenta.getSaldoInicial() + transaction.getMovimiento();

            if(saldoDisponible < 0){
                    return new ResponseEntity<Object>("Saldo no disponible.", HttpStatus.BAD_REQUEST);
            }

            Movimiento movimiento = new Movimiento();
            movimiento.setTipo(transaction.getTipo());
            movimiento.setMovimiento(transaction.getMovimiento());
            movimiento.setCuenta(cuenta);
            movimiento.setFecha(LocalDateTime.now());
            movimiento.setSaldoDisponible(saldoDisponible);
            movimientoRepository.save(movimiento);

            cuenta.setSaldoInicial(saldoDisponible);
            cuentaService.update(cuenta.getCuentaid(),cuenta);

            return new ResponseEntity<Object>(movimiento, HttpStatus.CREATED);

        }else{
            return new ResponseEntity<Object>("La cuenta no existe.", HttpStatus.BAD_REQUEST);
        }

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

    @Override
    public List<Reporte> reporteByFechaAndUsuario(Integer clienteId, String fechaInicio, String fechafin) {

        ResponseEntity<List<Cuenta>> response = cuentaService.fingByCliente(clienteId);

        if(response.getStatusCode().is2xxSuccessful()){
            log.info("response status "+ response.getStatusCodeValue());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            LocalDate localDateIni = LocalDate.parse(fechaInicio, formatter);

            LocalDate localDateEnd = LocalDate.parse(fechafin, formatter);

            LocalDateTime localDateTimeIni = LocalDateTime.of(localDateIni, LocalTime.MIN);

            LocalDateTime localDateTimeEnd = LocalDateTime.of(localDateEnd, LocalTime.MAX);

            List<Reporte> reportes= new ArrayList<>();

            log.info("busqueda por rango");
            for (Cuenta cuenta: response.getBody()){
                movimientoRepository.findAll().stream().filter(
                        movimiento -> {
                            if(movimiento.getCuenta().getCuentaid() == cuenta.getCuentaid() &&
                                    movimiento.getFecha().isAfter(localDateTimeIni)
                                    && movimiento.getFecha().isBefore(localDateTimeEnd)){
                                return true;
                            }
                            return false;
                        }).forEach(movmientos ->{
                            log.info(movmientos.toString());
                            Reporte reporte= new Reporte();
                            reporte.setCliente(cuenta.getCliente().getNombres());
                            reporte.setNumeroCuenta(cuenta.getNumero());
                            reporte.setMovimiento(movmientos.getMovimiento());
                            reporte.setFecha(movmientos.getFecha().toString());
                            reporte.setTipo(movmientos.getTipo().name());
                            reporte.setSaldoDisponible(movmientos.getSaldoDisponible());
                            reporte.setSaldoInicial(cuenta.getSaldoInicial());
                            reporte.setEstado(cuenta.isEstado());
                            reportes.add(reporte);
                    });


            }
            log.info("reportes sixe " + reportes.size());
            return reportes;
        }
        log.info("No se encontro cuentas ." );
        return null;
    }
}
