/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.test.microservicioCuentas.cuentas.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
@Entity
@Table(name = "MOVIMIENTO")
@SequenceGenerator(name = "moviento_seq", sequenceName = "MOVIMIENTO_SQ", allocationSize = 1)
public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moviento_seq")
    @Column(name = "\"id_movimiento\"")
    private Long idMovimiento;
    @Column(name = "\"fecha\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "\"tipo_movimiento\"")
    private String tipoMovimiento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"valor\"")
    private BigDecimal valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"saldo\"")
    private BigDecimal saldo;
    @JoinColumn(name = "\"numero_cuenta\"", referencedColumnName = "\"numero_cuenta\"")
    @ManyToOne
    private Cuenta numeroCuenta;

    public Movimiento() {
    }

    public Movimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Movimiento(Long idMovimiento, String tipoMovimiento, BigDecimal valor, BigDecimal saldo) {
        this.idMovimiento = idMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.valor = valor;
        this.saldo = saldo;
    }

    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Cuenta getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Cuenta numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

 

    @Override
    public String toString() {
        return "com.devsu.test.microservicioclientes.clientes.Movimiento[ idMovimiento=" + idMovimiento + " ]";
    }

}
