package org.example.daos.Impl;

import org.example.daos.IExamenRepositorio;
import org.example.models.Examen;

import java.util.Arrays;
import java.util.List;

public class ExamenRepositoryImpl implements IExamenRepositorio {
    @Override
    public List<Examen> findAll() {
        return Arrays.asList(new Examen(5L, "Matematicas"), new Examen(6L, "Lenguaje"),
                new Examen(7L, "Historia"));

    }
}
