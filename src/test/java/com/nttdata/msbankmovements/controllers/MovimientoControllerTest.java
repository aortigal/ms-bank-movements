package com.nttdata.msbankmovements.controllers;

import com.nttdata.msbankmovements.entity.Cuenta;
import com.nttdata.msbankmovements.entity.Movimiento;
import com.nttdata.msbankmovements.enums.MovimientoEnum;
import com.nttdata.msbankmovements.services.IMovimientoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MovimientoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IMovimientoService movimientoService;

    @Test
    void getListALl() throws Exception {
        mvc.perform(get("/api/movimientos"))
                .andExpect(status().isOk());
    }

    @Test
    void getListById() throws Exception {

        Integer id =1;
        Movimiento movimiento = new Movimiento(id, LocalDateTime.now(), MovimientoEnum.AHORRO,500.0,1000.0, new Cuenta());

        when(movimientoService.getById(id)).thenReturn(movimiento);

        mvc.perform(get("/api/movimientos/" + id))
                .andExpect(status().isOk());
    }

}
