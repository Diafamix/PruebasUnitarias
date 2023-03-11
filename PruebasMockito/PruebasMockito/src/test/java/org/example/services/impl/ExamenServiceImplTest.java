package org.example.services.impl;

import org.example.daos.IExamenRepositorio;
import org.example.daos.Impl.ExamenRepositoryImpl;
import org.example.models.Examen;
import org.example.services.IExamenService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExamenServiceImplTest {

    @Test
    void findExamenPorNombre() {
        IExamenRepositorio repository = new ExamenRepositoryImpl();
        IExamenService service = new ExamenServiceImpl(repository);

        Examen examen = service.findExamenPorNombre("Matematicas");

        assertNotNull(examen);
        assertEquals(5L, examen.getId());
        assertEquals("Matematicas", examen.getNombre());
    }
}