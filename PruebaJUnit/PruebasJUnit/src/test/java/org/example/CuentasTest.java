package org.example;


import org.example.exceptions.DineroInsuficienteException;
import org.example.models.Banco;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CuentasTest {

    @Test
    void testNombreCuenta() {
        Cuentas cuenta = new Cuentas("Andres", new BigDecimal("1000.12345"));

        String esperado = "Andres";
        String real = cuenta.getPersona();

        assertEquals(esperado, real);
    }

    @Test
    void testSaldoCuenta() {
        Cuentas cuentas = new Cuentas("Andres", new BigDecimal("1000.12345"));

        assertEquals(1000.12345, cuentas.getSaldo().doubleValue());
        assertFalse(cuentas.getSaldo().compareTo(BigDecimal.ZERO) < 0);
    }

    @Test
    void testReferenciasDeCuenta() {
        Cuentas cuenta1 = new Cuentas("John Doe", new BigDecimal("8900.9997"));
        Cuentas cuenta2 = new Cuentas("John Doe", new BigDecimal("8900.9997"));

        // Compruena direcciones de memoria al no apuntar al mismo sitio no pasara el test
        //assertNotEquals(cuenta1, cuenta2);
        // Son dos objetos distintos no compara por valor pero al refactorizar el equals comprueba el valor
        assertEquals(cuenta1, cuenta2);

    }

    @Test
    void testDebitoCUenta() {
        Cuentas cuenta = new Cuentas("Andres", new BigDecimal("1000.12345"));
        cuenta.debito(new BigDecimal(100));

        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        Cuentas cuenta = new Cuentas("Andres", new BigDecimal("1000.12345"));
        cuenta.credito(new BigDecimal(100));

        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.12345", cuenta.getSaldo().toPlainString());
    }

    @Test
    void dineroInsuficienteExceptionCuenta() {
        Cuentas cuenta = new Cuentas("Andres", new BigDecimal("1000.12345"));

        Exception exception = assertThrows(DineroInsuficienteException.class, () -> {
           cuenta.debito(new BigDecimal(1500));
        });

        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";

        assertEquals(esperado, actual);
    }

    @Test
    void testTransferirDineroCuentas() {
        Cuentas cuenta1 = new Cuentas("Jhon Doe", new BigDecimal("2500"));
        Cuentas cuenta2 = new Cuentas("Andres", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.setNombre("Banco del Estado");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());
    }

    @Test
    void testRelacionBancoCuentas() {
        Cuentas cuenta1 = new Cuentas("Jhon Doe", new BigDecimal("2500"));
        Cuentas cuenta2 = new Cuentas("Andres", new BigDecimal("1500.8989"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);
        banco.setNombre("Banco del Estado");

        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));
        assertEquals("1000.8989", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());
        // Comprueba que le banco tiene dos cuentas
        assertEquals(2, banco.getCuentas().size());
        assertEquals("Banco del Estado", cuenta1.getBanco().getNombre());

        // Validar que la cuenta pertenezca a un usuario
        assertEquals("Andres", banco.getCuentas().stream()
                .filter(c -> c.getPersona().equals("Andres"))
                .findFirst()
                .get().getPersona());

        assertTrue(banco.getCuentas().stream()
                .filter(c -> c.getPersona().equals("Andres"))
                .findFirst()
                .isPresent());

        assertTrue(banco.getCuentas().stream()
                .anyMatch(c -> c.getPersona().equals("Andres"))sas);
    }
}