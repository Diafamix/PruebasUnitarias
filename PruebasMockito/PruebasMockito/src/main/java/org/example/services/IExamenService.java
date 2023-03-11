package org.example.services;

import org.example.models.Examen;

public interface IExamenService {

    Examen findExamenPorNombre(String nombre);
}
