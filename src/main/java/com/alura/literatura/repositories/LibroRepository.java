package com.alura.literatura.repositories;



import com.alura.literatura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Método para buscar libros por título
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Método para listar libros por idioma
    List<Libro> findByIdioma(String idioma);
}
