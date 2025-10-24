/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.test.microservicioCuentas.cuentas.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
@Entity
@Table(name = "CUENTA")
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull(message="El numero de cuenta es obligatorio")
    @NotBlank(message="El numero de cuenta no puede estar vacio")
    @Size(min = 1, max = 50)
    @Column(name = "\"numero_cuenta\"", nullable=false,unique=true)
    private String numeroCuenta;
    @Basic(optional = false)
     @NotNull(message="El tipo de cuenta es obligatorio")
    @NotBlank(message="El tipo de cuenta no puede estar vacio")
    @Size(min = 1, max = 50)
    @Column(name = "\"tipo_cuenta\"", nullable=false)
    private String tipoCuenta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull(message="El saldo es obligatorio")
    @Column(name = "\"saldo_inicial\"", nullable=false)
    private BigDecimal saldoInicial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "\"saldo_actual\"", nullable=false)
    private BigDecimal saldoActual;
    @Size(max = 50)
    @Column(name = "\"id_cliente\"", nullable=false)
    private String idCliente;
    @Column(name = "\"estado\"")
    private Character estado;
    

    public Cuenta() {
    }

    public Cuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Cuenta(String numeroCuenta, String tipoCuenta, BigDecimal saldoInicial, BigDecimal saldoActual) {
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.saldoActual = saldoActual;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cuenta{" + "numeroCuenta=" + numeroCuenta + ", tipoCuenta=" + tipoCuenta + ", saldoInicial=" + saldoInicial + ", saldoActual=" + saldoActual + ", idCliente=" + idCliente + ", estado=" + estado + '}';
    }



           
}
