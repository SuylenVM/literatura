package com.alura.literatura;

import com.alura.literatura.controllers.AutorController;
import com.alura.literatura.controllers.LibroController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@SpringBootApplication

@Component
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
	private LibroController libroController;

	@Autowired
	private AutorController autorController;

	private final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) {
		while (true) {
			System.out.println("\n--- Catálogo de Libros ---");
			System.out.println("1. Buscar libro por título");
			System.out.println("2. Listar libros registrados");
			System.out.println("3. Listar autores registrados");
			System.out.println("4. Listar autores vivos en un año determinado");
			System.out.println("5. Listar libros por idiomas");
			System.out.println("6. Buscar libro en la API y registrarlo");
			System.out.println("0. Salir");
			System.out.print("Selecciona una opción: ");

			try {
				if (!scanner.hasNextInt()) {
					System.out.println("Error: Ingresa un número válido.");
					scanner.next(); // Limpia entrada inválida
					continue;
				}

				int opcion = scanner.nextInt();
				scanner.nextLine(); // IMPORTANTE: Limpiar buffer después de nextInt()

				switch (opcion) {
					case 1:
						libroController.buscarLibroPorTitulo();
						break;
					case 2:
						libroController.listarLibros();
						break;
					case 3:
						autorController.listarAutores();
						break;
					case 4:
						autorController.listarAutoresVivosEnAnio();
						break;
					case 5:
						libroController.listarLibrosPorIdioma();
						break;
					case 6:
						libroController.buscarLibroEnApiYRegistrar();
						break;
					case 0:
						System.out.println("¡Hasta luego!");
						System.exit(0);
						break;
					default:
						System.out.println("Opción no válida. Inténtalo de nuevo.");
				}
			} catch (Exception e) {
				System.out.println("Error: Ingresa un número válido.");
				scanner.nextLine(); // Limpiar buffer para evitar errores en el siguiente ciclo
			}
		}
	}
}

