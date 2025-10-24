/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.test.microservicioCuentas.cuentas.repository;

import com.devsu.test.microservicioCuentas.cuentas.entity.Cuenta;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
public interface CuentaRepository extends CrudRepository<Cuenta, String> {

   // Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

    

    boolean existsByNumeroCuenta(String numeroCuenta);

    @Override
    List<Cuenta> findAll();

    @Query(value = "SELECT * FROM CUENTA WHERE \"id_cliente\" = :idCliente", nativeQuery = true)
    @Transactional(readOnly = true)
    public List<Cuenta> findByIdCliente(@Param("idCliente") String idCliente);

    
     @Query(value = "SELECT * FROM CUENTA  WHERE  \"numero_cuenta\" = :numeroCuenta", nativeQuery = true)
    @Transactional(readOnly = true)
    public Cuenta findByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);
}
