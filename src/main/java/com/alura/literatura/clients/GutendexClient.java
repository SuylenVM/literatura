package com.alura.literatura.clients;

import com.alura.literatura.models.Autor;
import com.alura.literatura.models.Libro;
import com.alura.literatura.repositories.AutorRepository;
import com.alura.literatura.repositories.LibroRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GutendexClient {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    public GutendexClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        List<Libro> libros = new ArrayList<>();

        try {
            // Codificar correctamente la URL con espacios
            String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String apiUrl = "https://gutendex.com/books/?search=" + tituloCodificado;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir la respuesta de la API
            System.out.println("Respuesta API Gutendex:");
            System.out.println(response.body());

            JsonNode jsonNode = objectMapper.readTree(response.body());

            // Si la API no devuelve los resultados
            if (!jsonNode.has("results") || jsonNode.get("results").isEmpty()) {
                System.out.println("No se encontraron libros en la API.");
                return libros; // Devuelve una lista vacía
            }

            for (JsonNode bookNode : jsonNode.get("results")) {
                Libro libro = new Libro();
                libro.setTitulo(bookNode.get("title").asText());
                libro.setIdioma(bookNode.get("languages").get(0).asText()); // Tomar el primer idioma
                libro.setNumeroDescargas(bookNode.get("download_count").asInt());

                // Tomar el primer autor si existe
                JsonNode authors = bookNode.get("authors");
                Autor autor;

                if (authors != null && authors.isArray() && authors.has(0)) {
                    JsonNode firstAuthor = authors.get(0);
                    String nombreAutor = firstAuthor.get("name").asText();

                    // Verificar si el autor ya existe en la base de datos
                    Optional<Autor> autorExistente = autorRepository.findByNombre(nombreAutor);
                    if (autorExistente.isPresent()) {
                        autor = autorExistente.get();
                    } else {
                        autor = new Autor();
                        autor.setNombre(nombreAutor);
                        if (firstAuthor.has("birth_year")) {
                            autor.setAnioNacimiento(firstAuthor.get("birth_year").asInt());
                        }
                        if (firstAuthor.has("death_year")) {
                            autor.setAnioFallecimiento(firstAuthor.get("death_year").asInt());
                        }
                        autor = autorRepository.save(autor); // Guardar en la base de datos
                    }

                } else {
                    autor = new Autor();
                    autor.setNombre("Autor desconocido");
                    autor = autorRepository.save(autor);
                }

                libro.setAutor(autor);
                libroRepository.save(libro); // Con esto se guarda el libro en la base de datos
                libros.add(libro);
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("Error al consumir la API Gutendex: " + e.getMessage());
        }

        return libros;
    }
}

