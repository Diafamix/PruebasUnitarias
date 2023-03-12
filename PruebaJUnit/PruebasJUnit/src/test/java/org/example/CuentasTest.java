package org.example;


import org.example.exceptions.DineroInsuficienteException;
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
}