/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.test.microservicioCuentas.cuentas.controller;

import com.devsu.test.microservicioCuentas.cuentas.entity.Cuenta;
import com.devsu.test.microservicioCuentas.cuentas.entity.Movimiento;
import com.devsu.test.microservicioCuentas.cuentas.service.CuentaService;
import com.devsu.test.microservicioCuentas.cuentas.service.MovimientoService;
import com.devsu.test.microservicioCuentas.cuentas.service.ReporteService;
import dto.ReporteCuentasDto;
import dto.ReporteMovimientosDto;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    private static final Logger log = LoggerFactory.getLogger(MovimientoController.class);

    /**
     * servicio de movimientos
     */
    private final MovimientoService service;

    /**
     * servicio de movimientos
     */
    private final CuentaService serviceCuenta;
    
    /**
     * servicio de reporte jasper
     */
     private final ReporteService serviceReporte;

    public MovimientoController(MovimientoService pService, CuentaService pServiceCuenta,ReporteService pServiceReporte) {
        this.service = pService;
        this.serviceCuenta = pServiceCuenta;
        this.serviceReporte= pServiceReporte;
    }

    @GetMapping
    public List<Movimiento> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Movimiento get(@PathVariable Long id) {
        return service.obtener(id);
    }

    @PostMapping
    public ResponseEntity<Movimiento> crear(@Valid @RequestBody Movimiento pMovimiento) {
        Movimiento creado = service.crear(pMovimiento);
        return ResponseEntity.created(URI.create("/movimientos/" + creado.getIdMovimiento())).body(creado);
    }

    @PutMapping("/{id}")
    public Movimiento actualizar(@PathVariable Long id, @Valid @RequestBody Movimiento pMovimiento) {
        Movimiento creado = service.actualizar(id, pMovimiento);
        return creado;
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);

    }

    /**
     * F4: reporte JSON simple metodo para obtener el reporte de movimientos de
     * un cliente y de sus cuentas
     *
     * @param idCliente id cliente
     * @param fechaInicio fecha inicio
     * @param fechaFin fecha fin
     * @return lista de cuentas con sus movimientos
     */
    @GetMapping("/reporteMovimientos")
    public ArrayList<ReporteCuentasDto> reporteCuentas(@RequestParam String idCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        log.info("reporte cuentas inicio idCliente " + idCliente + "-" + fechaInicio + "- " + fechaFin);
        ArrayList<ReporteCuentasDto> reporteCuentas = new ArrayList<>();
        List<Cuenta> cuentas = new ArrayList<>();
        cuentas = serviceCuenta.findByIdCliente(idCliente);

        log.info("cuentas " + cuentas.toString());

        for (int i = 0; i < cuentas.size(); i++) {
            log.info("cuenta  " + cuentas.get(i).toString());
            ReporteCuentasDto rep = new ReporteCuentasDto();
            log.info("fechaInicio " + fechaInicio);
            log.info("fechaFin " + fechaFin);

            List<Movimiento> movs = service.findByNumeroCuentaBetweeFecha(cuentas.get(i).getNumeroCuenta(), fechaInicio, fechaFin);
            List<ReporteMovimientosDto> movsDto= new ArrayList<>();
             for (int j = 0; j < movs.size(); j++){
                 movsDto.add(new ReporteMovimientosDto(movs.get(j)));
                 
             }
            rep.setIdCliente(idCliente);
            rep.setMovimientos(movsDto);
            reporteCuentas.add(rep);
        }

        log.info("resultado " + reporteCuentas.toString());
        return reporteCuentas;
    }
    
    
    @GetMapping(value="/reportePdf")
      public ResponseEntity<byte[]> reporteCuentasPdf(@RequestParam String idCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        log.info("reporteCuentasPdf cuentas inicio idCliente " + idCliente + "-" + fechaInicio + "- " + fechaFin);
        ArrayList<ReporteCuentasDto> reporteCuentas = new ArrayList<>();
        List<Cuenta> cuentas = new ArrayList<>();
        
        cuentas = serviceCuenta.findByIdCliente(idCliente);

        log.info("cuentas " + cuentas.toString());

        for (int i = 0; i < cuentas.size(); i++) {
            log.info("cuenta  " + cuentas.get(i).toString());
            ReporteCuentasDto rep = new ReporteCuentasDto();
            log.info("fechaInicio " + fechaInicio);
            log.info("fechaFin " + fechaFin);

            List<Movimiento> movs = service.findByNumeroCuentaBetweeFecha(cuentas.get(i).getNumeroCuenta(), fechaInicio, fechaFin);
            List<ReporteMovimientosDto> movsDto= new ArrayList<>();
             for (int j = 0; j < movs.size(); j++){
                 movsDto.add(new ReporteMovimientosDto(movs.get(j)));
                 
             }
            rep.setIdCliente(idCliente);
            rep.setMovimientos(movsDto);
            reporteCuentas.add(rep);
        }

        log.info("resultado " + reporteCuentas.toString());
        byte[] pdf=serviceReporte.generarReportePDF(reporteCuentas);
        
        HttpHeaders headers=new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.inline().filename("reporteCuentas.pdf").build()
        );
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=reporteCuentas.pdf").contentType(MediaType.APPLICATION_PDF).body(pdf);
            
    }

      
          @GetMapping(value="/reporteMovimientosPdf")
      public ResponseEntity<byte[]> reporteMovimientosPdf(@RequestParam String idCliente,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        log.info("reporteCuentasPdf cuentas inicio idCliente " + idCliente + "-" + fechaInicio + "- " + fechaFin);
        ArrayList<ReporteCuentasDto> reporteCuentas = new ArrayList<>();
        List<Cuenta> cuentas = new ArrayList<>();
         List<ReporteMovimientosDto> movsDto= new ArrayList<>();
        
        cuentas = serviceCuenta.findByIdCliente(idCliente);

        log.info("cuentas " + cuentas.toString());

        for (int i = 0; i < cuentas.size(); i++) {
            log.info("cuenta  " + cuentas.get(i).toString());
            ReporteCuentasDto rep = new ReporteCuentasDto();
            log.info("fechaInicio " + fechaInicio);
            log.info("fechaFin " + fechaFin);

            List<Movimiento> movs = service.findByNumeroCuentaBetweeFecha(cuentas.get(i).getNumeroCuenta(), fechaInicio, fechaFin);
           
             for (int j = 0; j < movs.size(); j++){
                 movsDto.add(new ReporteMovimientosDto(movs.get(j)));
                 
             }
            rep.setIdCliente(idCliente);
            rep.setMovimientos(movsDto);
            reporteCuentas.add(rep);
        }

        log.info("resultado " + reporteCuentas.toString());
        byte[] pdf=serviceReporte.generarReporteMovPDF(movsDto);
        
        HttpHeaders headers=new HttpHeaders();
        headers.setContentDisposition(
                ContentDisposition.inline().filename("reporteMovimientos.pdf").build()
        );
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=reporteMovimientos.pdf").contentType(MediaType.APPLICATION_PDF).body(pdf);
            
    }
}
