/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.test.microservicioCuentas.cuentas.controller;

import com.devsu.test.microservicioCuentas.cuentas.entity.Cuenta;
import com.devsu.test.microservicioCuentas.cuentas.entity.Movimiento;
import com.devsu.test.microservicioCuentas.cuentas.service.CuentaService;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:alruano@ittraktys.com">Ana
 * Ruano</a>
 */
@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    private final CuentaService service;

    public CuentaController(CuentaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cuenta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Cuenta get(@PathVariable String numeroCuenta) {
        return service.findByNumeroCuenta(numeroCuenta);
    }

    @PostMapping
    public ResponseEntity<Cuenta> crear(@Valid @RequestBody Cuenta pCuenta) {
        Cuenta creado = service.crear(pCuenta);
        if (StringUtils.isBlank(pCuenta.getNumeroCuenta())) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.created(URI.create("/cuentas/" + creado.getNumeroCuenta())).body(creado);
    }

    @PutMapping("/{numeroCuenta}")
    public Cuenta actualizar(@PathVariable String numeroCuenta, @Valid @RequestBody Cuenta pCuenta) {
        Cuenta creado = service.actualizar(numeroCuenta, pCuenta);
        return creado;
    }

    @DeleteMapping("/{numeroCuenta}")
    public void eliminar(@PathVariable String numeroCuenta) {
        service.eliminar(numeroCuenta);

    }

    
    @PostMapping("/movimientos")
    public Movimiento registraMovimiento(
            @RequestParam String numeroCuenta,
            @RequestParam String tipoMovimiento,
            @RequestParam double valor) {
        return service.registrarMovimiento(numeroCuenta, tipoMovimiento, valor);
    }
    
      
 
}
