package org.example.daos;

import org.example.models.Examen;

import java.util.List;

public interface IExamenRepositorio {
    List<Examen> findAll();
}
