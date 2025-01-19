package com.alura.literatura.controllers;

import com.alura.literatura.clients.GutendexClient;
import com.alura.literatura.models.Libro;
import com.alura.literatura.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller
public class LibroController {

    @Autowired
    private GutendexClient gutendexClient;

    @Autowired
    private LibroRepository libroRepository;

    private final Scanner scanner = new Scanner(System.in);

    public void buscarLibroEnApiYRegistrar() {
        System.out.print("Ingresa el título del libro a buscar en la API: ");
        String titulo = scanner.nextLine();

        List<Libro> librosEncontrados = gutendexClient.buscarLibrosPorTitulo(titulo);

        if (librosEncontrados.isEmpty()) {
            System.out.println("No se encontraron libros en la API.");
        } else {
            System.out.println("Se han registrado los siguientes libros:");
            for (Libro libro : librosEncontrados) {
                System.out.println(libro);
            }
        }
    }

    public void buscarLibroPorTitulo() {
    }

    public void listarLibros() {
    }

    public void listarLibrosPorIdioma() {
    }
}

