/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;

import java.util.List;

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
public class ReporteCuentasDto implements Serializable {
   
    private String idCliente;    
    private List<ReporteMovimientosDto> movimientos;

    public ReporteCuentasDto() {
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

  
      public List<ReporteMovimientosDto> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<ReporteMovimientosDto> movimientos) {
        this.movimientos = movimientos;
    }

    @Override
    public String toString() {
        return "ReporteCuentasDto{" + "idCliente=" + idCliente + ", movimientos=" + movimientos + '}';
    }

   
    
}
