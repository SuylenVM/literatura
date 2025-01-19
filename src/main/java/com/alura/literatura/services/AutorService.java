package com.alura.literatura.services;


import com.alura.literatura.models.Autor;
import com.alura.literatura.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    // Guardar un autor en la base de datos
    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    // Listar todos los autores
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    // Listar autores vivos en un año específico
    public List<Autor> listarAutoresVivosEnAnio(Integer anio) {
        return autorRepository.findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(anio, anio);
    }
}



