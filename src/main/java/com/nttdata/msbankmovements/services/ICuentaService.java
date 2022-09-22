package com.nttdata.msbankmovements.services;

import com.nttdata.msbankmovements.entity.Cuenta;
import org.springframework.http.ResponseEntity;

public interface ICuentaService {

    ResponseEntity<Cuenta> getFindById(String id);
}
