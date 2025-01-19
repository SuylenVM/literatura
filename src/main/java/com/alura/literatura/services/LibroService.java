package com.alura.literatura.services;


import com.alura.literatura.clients.GutendexClient;
import com.alura.literatura.models.Libro;
import com.alura.literatura.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private GutendexClient gutendexClient;

    // Guardar un libro en la base de datos
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    // Buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Listar todos los libros
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // Listar libros por idioma
    public List<Libro> listarPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
    // Buscar libros por título en la API Gutendex y guardarlos en la base de datos
    public List<Libro> buscarEnApiYGuardar(String titulo) {
        List<Libro> libros = gutendexClient.buscarLibrosPorTitulo(titulo); // Llama al cliente Gutendex
        libros.forEach(libroRepository::save); // Guarda los libros obtenidos en la base de datos
        return libros;
    }

}
