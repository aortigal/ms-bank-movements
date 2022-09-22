package com.nttdata.msbankmovements.services.impl;

import com.nttdata.msbankmovements.entity.Cuenta;
import com.nttdata.msbankmovements.entity.Movimiento;
import com.nttdata.msbankmovements.services.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<Cuenta> getFindById(Integer id) {
        ResponseEntity<Cuenta> ratings_body = restTemplate.exchange("http://localhost:8071/api/cuentas/"+id,
                HttpMethod.GET, null, new ParameterizedTypeReference<Cuenta>() {});
        return ratings_body;
    }

    @Override
    public ResponseEntity<Cuenta> fingByNumeroCuenta(String numeroCuenta) {
        ResponseEntity<Cuenta> ratings_body = restTemplate.exchange("http://localhost:8071/api/cuentas/numero/"+numeroCuenta,
                HttpMethod.GET, null, new ParameterizedTypeReference<Cuenta>() {});
        return ratings_body;
    }

    @Override
    public ResponseEntity<Object> update(Integer id, Cuenta cuenta) {
        HttpEntity<Cuenta> entity = new HttpEntity<Cuenta>(cuenta);
        ResponseEntity<Object> ratings_body = restTemplate.exchange("http://localhost:8071/api/cuentas/"+id,
                HttpMethod.PUT, entity, new ParameterizedTypeReference<Object>() {});
        return ratings_body;
    }

    @Override
    public ResponseEntity<List<Cuenta>> fingByCliente(Integer clienteId) {
        ResponseEntity<List<Cuenta>> ratings_body = restTemplate.exchange("http://localhost:8071/api/cuentas/cliente/"+clienteId,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Cuenta>>() {});
        return ratings_body;
    }

}
