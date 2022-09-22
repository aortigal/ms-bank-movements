package com.nttdata.msbankmovements.services.impl;

import com.nttdata.msbankmovements.entity.Cuenta;
import com.nttdata.msbankmovements.services.ICuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public ResponseEntity<Cuenta> getFindById(String id) {
        ResponseEntity<Cuenta> ratings_body = restTemplate.exchange("http://localhost:8071/api/cuentas/"+id,
                HttpMethod.GET, null, new ParameterizedTypeReference<Cuenta>() {});
        return ratings_body;
    }
}
