/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.test.microservicioCuentas.cuentas.service;

import dto.ReporteCuentasDto;
import dto.ReporteMovimientosDto;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:alruano@ittraktys.com">Ana
 * Ruano</a>
 */
@Service
public class ReporteService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ReporteService.class);

    public byte[] generarReportePDF(List<ReporteCuentasDto> datos) {

        try {

            /**
             * cargar reporte plantilla
             */
            InputStream reporteStream = getClass().getResourceAsStream("/reportes/reporteEstadoCuenta.jasper");
            if(reporteStream==null){
                throw new IllegalStateException("No se encontro el reporte reporteEstadoCuenta.jasper");
            }
          //fuente de datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("titulo", "Reporte Estado de Cuentas y Movimientos");
            //se llena el reporte con el DTO
            JasperPrint print = JasperFillManager.fillReport(reporteStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(print);
        } catch (JRException ex) {
            log.error("Error a generar reporte", ex.getMessage());
            throw new RuntimeException("Error al generar PDF " + ex.getMessage());
        }
    }
    
       public byte[] generarReporteMovPDF(List<ReporteMovimientosDto> datos) {

        try {

            /**
             * cargar reporte plantilla
             */
            InputStream reporteStream = getClass().getResourceAsStream("/reportes/detalleMovimientos.jasper");
            if(reporteStream==null){
                throw new IllegalStateException("No se encontro el reporte detalleMovimientos.jasper");
            }
          //fuente de datos
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(datos);

            Map<String, Object> parametros = new HashMap<>();
         //   parametros.put("titulo", "Reporte Estado de Cuentas y Movimientos");
            //se llena el reporte con el DTO
            JasperPrint print = JasperFillManager.fillReport(reporteStream, parametros, dataSource);

            return JasperExportManager.exportReportToPdf(print);
        } catch (JRException ex) {
            log.error("Error a generar reporte", ex.getMessage());
            throw new RuntimeException("Error al generar PDF " + ex.getMessage());
        }
    }
}
