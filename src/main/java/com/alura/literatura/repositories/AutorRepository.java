package com.alura.literatura.repositories;



import com.alura.literatura.models.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Método para listar autores vivos en un año específico
    List<Autor> findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(Integer anioInicio, Integer anioFin);

    Optional<Autor> findByNombre(String nombreAutor);
}

