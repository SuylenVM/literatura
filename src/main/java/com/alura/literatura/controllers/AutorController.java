package com.alura.literatura.controllers;


import com.alura.literatura.models.Autor;
import com.alura.literatura.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class AutorController {

    @Autowired
    private AutorService autorService;

    private final Scanner scanner = new Scanner(System.in);

    // Opción 3: Listar autores registrados
    public void listarAutores() {
        List<Autor> autores = autorService.listarAutores();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en la base de datos.");
        } else {
            System.out.println("Listado de autores:");
            autores.forEach(System.out::println);
        }
    }

    // Opción 4: Listar autores vivos en un año específico
    public void listarAutoresVivosEnAnio() {
        System.out.print("Ingresa el año para buscar autores vivos: ");
        int anio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        List<Autor> autores = autorService.listarAutoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio + ".");
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autores.forEach(System.out::println);
        }
    }

    public void listarAutoresVivosPorAnio() {
    }
}
