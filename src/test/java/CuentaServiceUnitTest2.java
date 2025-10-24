
import com.devsu.test.microservicioCuentas.cuentas.entity.Cuenta;
import com.devsu.test.microservicioCuentas.cuentas.repository.CuentaRepository;
import com.devsu.test.microservicioCuentas.cuentas.service.CuentaService;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Ana Luisa Ruano Espina <a href="mailto:alruano@ittraktys.com">Ana
 * Ruano</a>
 */
@ExtendWith(MockitoExtension.class)
public class CuentaServiceUnitTest2 {
    @Mock
    private CuentaRepository repo;
    @InjectMocks
    private CuentaService service;
    
     @Test
    public void TestCuenta() throws Exception{
      
            Cuenta cuenta = new Cuenta();
            cuenta.setEstado('1');
            cuenta.setIdCliente("AB001");
            cuenta.setNumeroCuenta("ABC0021");
            cuenta.setSaldoActual(BigDecimal.ZERO);
            cuenta.setSaldoInicial(BigDecimal.ONE);
            cuenta.setTipoCuenta("AHORROS");
            
            when(repo.save(any(Cuenta.class))).thenReturn(cuenta);
            
          Cuenta resultado=service.crear(cuenta);
          
          assertEquals("ABC0021",resultado.getNumeroCuenta());
          verify(repo,times(1)).save(any(Cuenta.class));
                    
                   
        
    }
}
