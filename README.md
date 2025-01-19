# 📚 Catálogo de Libros - Literatura

## 🔗 Descripción del Proyecto
Literatura es una aplicación de consola desarrollada en Java utilizando Spring Boot y PostgreSQL. Permite a los usuarios buscar libros en la API [Gutendex](https://gutendex.com/) y registrar la información obtenida en una base de datos. Además, permite consultar los libros y autores registrados.

## ⚙️ Tecnologías Utilizadas
- Java 17
- Spring Boot 3.4.1
- Spring Data JPA
- PostgreSQL
- Hibernate
- Jackson (para manejo de JSON)
- Maven

## 💡 Características Principales
- Buscar libros por título en la API Gutendex y registrarlos en la base de datos.
- Listar libros registrados en la base de datos.
- Listar autores registrados.
- Consultar autores vivos en un año determinado.
- Listar libros por idioma (Español, Inglés, Francés, Portugués).
- Persistencia de datos en PostgreSQL.

## ✅ Requisitos Previos
Antes de ejecutar el proyecto, asegúrate de tener instalado:
- Java 17 o superior
- Maven
- PostgreSQL

## ⚖️ Configuración de PostgreSQL
1. **Crear la base de datos en PostgreSQL**:
   ```sql
   CREATE DATABASE literatura;
   ```
2. Configurar las credenciales en `src/main/resources/application.properties´:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literatura
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

## 🔄 Instalación y Ejecución
1. Clonar el repositorio:
   ```sh
   git clone https://github.com/tu_usuario/literatura.git
   cd literatura
   ```
2. Compilar el proyecto:
   ```sh
   mvn clean install
   ```
3. Ejecutar la aplicación:
   ```sh
   mvn spring-boot:run
   ```

## 🔮 Uso de la Aplicación
Al ejecutar la aplicación, verás el siguiente menú:
```
--- Catálogo de Libros ---
1. Buscar libro por título
2. Listar libros registrados
3. Listar autores registrados
4. Listar autores vivos en un año determinado
5. Listar libros por idiomas
6. Buscar libro en la API y registrarlo
0. Salir
Selecciona una opción:
```

Ejemplo de uso:
- Si eliges la opción **6**, la aplicación te pedirá un título de libro y lo buscará en la API.
- Si el libro existe, se registrará en la base de datos y se mostrará en pantalla.
- Luego puedes listar los libros guardados con la opción 2.

## 📊 Verificación de Datos en PostgreSQL
Para verificar que los datos se están guardando correctamente, puedes ejecutar:
```sql
SELECT * FROM libros;
SELECT * FROM autores;
```

## 🎨 Mejoras Futuras
- Implementar una interfaz gráfica.
- Agregar más filtros de búsqueda.
- Conectar con otras API de libros.

## 🎉 Contribuciones
Si deseas contribuir al proyecto, eres bienvenidX. Puedes abrir un Pull Request o crear un Issue en GitHub.



