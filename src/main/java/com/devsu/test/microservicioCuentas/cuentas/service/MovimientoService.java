/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.test.microservicioCuentas.cuentas.service;

import com.devsu.test.microservicioCuentas.cuentas.entity.Movimiento;
import com.devsu.test.microservicioCuentas.cuentas.repository.MovimientoRepository;
import java.time.LocalDate;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
@Service
public class MovimientoService {
    
    private static final Logger log = LoggerFactory.getLogger(MovimientoService.class);
    private final MovimientoRepository repo;
    
    public MovimientoService(MovimientoRepository pRepo) {
        this.repo = pRepo;
    }

    /**
     *
     * @return {@code List<Movimiento>}
     */
    public List<Movimiento> listar() {
        return this.repo.findAll();
    }
    
    @Transactional
    public Movimiento crear(Movimiento pMovimiento) {
        Movimiento creado;
        if (this.repo.existsByIdMovimiento(pMovimiento.getIdMovimiento())) {
            throw new IllegalArgumentException("El movimiento ya existe");
        } else {
            creado = repo.save(pMovimiento);
        }
        return creado;
        
    }
    
    public Movimiento obtener(Long pId) {
        return repo.findById(pId).orElseThrow(() -> new IllegalArgumentException("No existe movimiento"));
    }
    
    @Transactional
    public Movimiento actualizar(Long pId, Movimiento pMovimiento) {
        Movimiento creado = obtener(pId);
        
        creado.setFecha(pMovimiento.getFecha());
        creado.setIdMovimiento(pMovimiento.getIdMovimiento());
        creado.setNumeroCuenta(pMovimiento.getNumeroCuenta());
        creado.setSaldo(pMovimiento.getSaldo());
        creado.setTipoMovimiento(pMovimiento.getTipoMovimiento());
        creado.setValor(pMovimiento.getValor());
        repo.save(creado);
        
        return creado;
        
    }
    
    @Transactional
    public void eliminar(Long pId) {
        Movimiento creado = obtener(pId);
        repo.delete(creado);
        
    }
    
    public Movimiento findByFecha(String fecha) {
        
        return repo.findByFecha(fecha);
        
    }
    
    public List<Movimiento> findByNumeroCuentaBetweeFecha(String numeroCuenta, LocalDate fechaIni, LocalDate fechaFin) {
        log.info("findByNumeroCuentaBetweeFecha " + numeroCuenta);
          log.info("fechaIni " + fechaIni);
          log.info("fechaFin " + fechaFin);
        if (fechaIni == null || fechaFin == null) {
            throw new IllegalArgumentException("Fechas invalidas");
        }
        return repo.findByNumeroCuentaBetweeFecha(numeroCuenta, fechaIni, fechaFin);
        
    }
    
}
