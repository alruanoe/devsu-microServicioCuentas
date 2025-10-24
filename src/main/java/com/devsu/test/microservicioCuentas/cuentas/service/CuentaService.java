/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.test.microservicioCuentas.cuentas.service;

import com.devsu.test.microservicioCuentas.cuentas.entity.Cuenta;
import com.devsu.test.microservicioCuentas.cuentas.entity.Movimiento;
import com.devsu.test.microservicioCuentas.cuentas.repository.CuentaRepository;
import com.devsu.test.microservicioCuentas.cuentas.repository.MovimientoRepository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
@Service
public class CuentaService {
    
    
    private static final Logger log = LoggerFactory.getLogger(CuentaService.class);


    private final CuentaRepository repo;
    private final MovimientoRepository repoMovimiento;

    public CuentaService(CuentaRepository pRepo, MovimientoRepository pRepoMovimiento) {
        this.repo = pRepo;
        this.repoMovimiento = pRepoMovimiento;
    }

    /**
     * Obtiene todas las cuentas
     *
     * @return {@code List<Cuenta>}
     */
    public List<Cuenta> listar() {
        return this.repo.findAll();
    }

    /**
     * Metodo para crear una cuenta
     *
     * @param pCuenta cuenta a crear
     * @return retorna la cuenta creada
     */
    @Transactional
    public Cuenta crear(Cuenta pCuenta) {
        Cuenta creado;
        if (StringUtils.isBlank(pCuenta.getNumeroCuenta())) {
            throw new IllegalArgumentException("El numero de cuenta no puede estar vacio");
        }

        if (StringUtils.isBlank(pCuenta.getTipoCuenta())) {
            throw new IllegalArgumentException("El tipo de cuenta no puede estar vacio");
        }

        if (this.repo.existsByNumeroCuenta(pCuenta.getNumeroCuenta())) {
            throw new IllegalArgumentException("La cuenta ya existe");
        }
        creado = repo.save(pCuenta);

        return creado;
    }

    /**
     * Metodo para obtener una cuenta por su numero de cuenta
     *
     * @param numeroCuenta numero de la cuenta
     * @return cuenta encontrada
     */
    public Cuenta findByNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("Numero de cuenta vacio o nulo");
        }
        return repo.findByNumeroCuenta(numeroCuenta);
    }

    /**
     * Metodo para actualizar una cuenta
     *
     * @param pNumeroCuenta numero de cuenta a actualizad
     * @param pCuenta cuenta a actualizar
     * @return cuenta actualizada
     */
    @Transactional
    public Cuenta actualizar(String pNumeroCuenta, Cuenta pCuenta) {
        Cuenta creado = findByNumeroCuenta(pNumeroCuenta);

        creado.setEstado(pCuenta.getEstado());
        creado.setIdCliente(pCuenta.getIdCliente());
        creado.setNumeroCuenta(pCuenta.getNumeroCuenta());
        creado.setSaldoActual(pCuenta.getSaldoActual());
        creado.setSaldoInicial(pCuenta.getSaldoInicial());
        creado.setTipoCuenta(pCuenta.getTipoCuenta());

        repo.save(creado);

        return creado;

    }

    /**
     * Metodo para eliminar una cuenta
     *
     * @param numeroCuenta numero de cuenta a eliminar
     */
    @Transactional
    public void eliminar(String numeroCuenta) {
        Cuenta creado = findByNumeroCuenta(numeroCuenta);
        repo.delete(creado);

    }

    /**
     * Metodo para obtener las cuentas de un cliente
     *
     * @param idCliente id del cliente
     * @return listado de cuentas
     */
    public List<Cuenta> findByIdCliente(String idCliente) {
        log.info("findByIdCliente "+idCliente);
        return repo.findByIdCliente(idCliente);

    }

    /**
     * Metodo para registrar un movimiento en la cuenta
     *
     * @param numeroCuenta numero de cuenta a registrar un movimiento
     * @param tipoMovimiento tipo de movimiento {@code "DEBITO"} retiro de
     * cuenta, {@code "CREDITO"} ingreso a cuenta
     * @param valorDouble valor del movimiento
     * @return movimiento realizado
     */
    public Movimiento registrarMovimiento(String numeroCuenta, String tipoMovimiento, double valorDouble) {
        Cuenta cuenta = repo.findByNumeroCuenta(numeroCuenta);
        Movimiento mov = new Movimiento();
        BigDecimal nuevoSaldo = cuenta.getSaldoActual() == null ? BigDecimal.ZERO : cuenta.getSaldoActual();
        BigDecimal valor;
        if (cuenta == null) {
            throw new IllegalArgumentException("La cuenta no existe");
        }
              if (tipoMovimiento.toUpperCase().equalsIgnoreCase("DEBITO")) {
            if (valorDouble <= 0) {
                valorDouble = Math.abs(valorDouble);
            }

            //  nuevoSaldo = BigDecimal.valueOf(nuevoSaldo.doubleValue() - valorDouble);
            valor = BigDecimal.valueOf(valorDouble).abs();

            if (nuevoSaldo.compareTo(valor) < 0) {
                throw new IllegalArgumentException("Saldo no disponible");
            }
            nuevoSaldo = nuevoSaldo.subtract(valor);
        } else {
            //credito
            if (valorDouble < 0) {
                valorDouble = Math.abs(valorDouble);
            }
            valor = BigDecimal.valueOf(valorDouble).abs();
            nuevoSaldo = nuevoSaldo.add(valor);
        }

        cuenta.setSaldoActual(nuevoSaldo);
        repo.save(cuenta);
        mov.setFecha(new Date());
        mov.setIdMovimiento(Long.valueOf("0"));
        mov.setNumeroCuenta(cuenta);
        mov.setSaldo(valor);
        mov.setValor(tipoMovimiento.toUpperCase().equalsIgnoreCase("DEBITO")?valor.negate():valor);
        mov.setTipoMovimiento(tipoMovimiento.toUpperCase());
        repoMovimiento.save(mov);

        return mov;
    }
  

}
