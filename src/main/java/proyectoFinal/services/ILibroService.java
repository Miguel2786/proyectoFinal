package proyectoFinal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import proyectoFinal.models.entities.Libro;

public interface ILibroService {
List<Libro> listar();
Page<Libro> listar(Pageable pageable);
void save(Libro libro);
void delete(Long id);
Libro findById(Long id);
Page<Libro> findByGenero(Pageable pageable,String genero);
Page<Libro> findByAutor(Pageable pageable,String autor);
}
