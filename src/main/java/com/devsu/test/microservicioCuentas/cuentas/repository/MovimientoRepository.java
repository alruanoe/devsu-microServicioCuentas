/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.test.microservicioCuentas.cuentas.repository;

import com.devsu.test.microservicioCuentas.cuentas.entity.Movimiento;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
public interface MovimientoRepository extends CrudRepository<Movimiento, Long> {

    Optional<Movimiento> findByIdMovimiento(Long idMovimiento);

    boolean existsByIdMovimiento(Long idMovimiento);

    @Override
    List<Movimiento> findAll();

    @Query(value = "SELECT * FROM MOVIMIENTO m WHERE fecha = :fecha", nativeQuery = true)
    @Transactional(readOnly = true)
    public Movimiento findByFecha(@Param("fecha") String fecha);

    @Query(value = "SELECT * FROM MOVIMIENTO m WHERE \"numero_cuenta\" = :numeroCuenta\n"
            + "AND \"fecha\" BETWEEN :fechaIni and :fechaFin \n"
            + "ORDER BY \"fecha\"", nativeQuery = true)
    @Transactional(readOnly = true)
    public List<Movimiento> findByNumeroCuentaBetweeFecha(@Param("numeroCuenta") String numeroCuenta, @Param("fechaIni") LocalDate fechaIni, @Param("fechaFin") LocalDate fechaFin);

}
