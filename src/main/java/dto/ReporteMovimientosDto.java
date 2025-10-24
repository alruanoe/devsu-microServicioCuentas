/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import com.devsu.test.microservicioCuentas.cuentas.entity.Cuenta;
import com.devsu.test.microservicioCuentas.cuentas.entity.Movimiento;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
public class ReporteMovimientosDto implements Serializable {

    private Long idMovimiento;
    private Date fecha;
    private String tipoMovimiento;
    private String numeroCuenta;
    private List<Cuenta> cuenta;
    private BigDecimal valor;
    private BigDecimal saldo;

    public ReporteMovimientosDto() {
    }

    public ReporteMovimientosDto(Movimiento movimiento) {
        List<Cuenta> cc = new ArrayList<>();
        cc.add(movimiento.getNumeroCuenta());
        this.idMovimiento = movimiento.getIdMovimiento();
        this.fecha = movimiento.getFecha();
        this.tipoMovimiento = movimiento.getTipoMovimiento();
        this.cuenta = cc;
        this.valor = movimiento.getValor();
        this.saldo = movimiento.getSaldo();
        this.numeroCuenta = movimiento.getNumeroCuenta().getNumeroCuenta();
    }

    public ReporteMovimientosDto(Long idMovimiento, Date fecha, String tipoMovimiento, String numeroCuenta, List<Cuenta> cuenta, BigDecimal valor, BigDecimal saldo) {
        this.idMovimiento = idMovimiento;
        this.fecha = fecha;
        this.tipoMovimiento = tipoMovimiento;
        this.numeroCuenta = numeroCuenta;
        this.cuenta = cuenta;
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

    public List<Cuenta> getCuenta() {
        return cuenta;
    }

    public void setCuenta(List<Cuenta> cuenta) {
        this.cuenta = cuenta;
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

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @Override
    public String toString() {
        return "ReporteMovimientosDto{" + "idMovimiento=" + idMovimiento + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento + ", numeroCuenta=" + numeroCuenta + ", cuenta=" + cuenta + ", valor=" + valor + ", saldo=" + saldo + '}';
    }

}
