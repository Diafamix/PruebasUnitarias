package org.example.services.impl;

import org.example.daos.IExamenRepositorio;
import org.example.models.Examen;
import org.example.services.IExamenService;

import java.util.Optional;

public class ExamenServiceImpl implements IExamenService {

    private IExamenRepositorio iExamenRepositorio;

    public ExamenServiceImpl(IExamenRepositorio iExamenRepositorio) {
        this.iExamenRepositorio = iExamenRepositorio;
    }

    @Override
    public Examen findExamenPorNombre(final String nombre) {
        Optional<Examen> examenOptional = iExamenRepositorio.findAll()
                .stream()
                .filter(e -> e.getNombre().contains(nombre))
                .findFirst();

        Examen examen = null;

        if(examenOptional.isPresent()) {
            examen = examenOptional.orElseThrow();
        }
        return examen;
    }
}
