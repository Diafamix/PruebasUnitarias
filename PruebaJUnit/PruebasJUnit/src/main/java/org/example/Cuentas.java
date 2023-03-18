package org.example;

import org.example.exceptions.DineroInsuficienteException;
import org.example.models.Banco;

import java.math.BigDecimal;
import java.util.Objects;

public class Cuentas {

    private String persona;
    private BigDecimal saldo;
    private Banco banco;

    public Cuentas(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public void debito(BigDecimal monto) {

        BigDecimal nuevoSaldo = this.saldo.subtract(monto);

        if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new DineroInsuficienteException("Dinero Insuficiente");
        }

        this.saldo = nuevoSaldo;
    }

    public void credito(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Cuentas)) {
            return false;
        }

        Cuentas cuentas = (Cuentas) o;
        if(this.persona == null || this.saldo == null) {
            return false;
        }
        return this.persona.equals(cuentas.getPersona()) && this.saldo.equals(cuentas.saldo);
    }


}
