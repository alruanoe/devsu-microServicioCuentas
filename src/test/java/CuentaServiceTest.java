/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import com.devsu.test.microservicioCuentas.cuentas.service.CuentaService;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.devsu.test.microservicioCuentas.cuentas.MsCuentasApp;
import com.devsu.test.microservicioCuentas.cuentas.entity.Cuenta;


/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:annaruanoespina@gmail.com">Ana
 * Ruano</a>
 */
@SpringBootTest(classes = MsCuentasApp.class)
public class CuentaServiceTest {

    /**
     * Servicio de cuenta
     */
    @Autowired
    CuentaService service;

    /**
     * Servicio de movimiento
     */
   // @Autowired
   // MovimientoService serviceMocimiento;

    @Test
    void CrearCuenta() {
        System.out.println("CrearCuenta ");
        Cuenta cuenta = new Cuenta();
        cuenta.setEstado('1');
        cuenta.setIdCliente("AB001");
        cuenta.setNumeroCuenta("ABC0022");
        cuenta.setSaldoActual(BigDecimal.ZERO);
        cuenta.setSaldoInicial(BigDecimal.ONE);
        cuenta.setTipoCuenta("AHORROS");

        Cuenta creado = service.crear(cuenta);
        if (creado != null) {
            System.out.println("se creo la cuenta " + creado.getNumeroCuenta());

        } else {
            System.out.println("no se creo la cuenta");
        }

        assertNotNull(creado);

    }

   // @Test
 /*   void CrearMovimiento() {
        System.out.println("CrearMovimiento ");
        Movimiento movimiento = new Movimiento();
        Integer correlativo = 0;
        System.out.println("correlativo " + correlativo);
        Cuenta cuentaRepo = service.findByNumeroCuenta("ABC001");
        movimiento.setFecha(new Date());
        movimiento.setIdMovimiento(Long.valueOf("0"));
        movimiento.setNumeroCuenta(cuentaRepo);
        movimiento.setSaldo(BigDecimal.ZERO);
        movimiento.setValor(BigDecimal.ONE);
        movimiento.setTipoMovimiento("DEBITO");

        Movimiento creado = serviceMocimiento.crear(movimiento);
        if (creado != null) {
            System.out.println("se creo el movimiento " + creado.getNumeroCuenta()+" -- "+creado.getIdMovimiento());

        } else {
            System.out.println("no se creo la movimiento");
        }

        assertNotNull(creado);

    }*/

}
