package proyectoFinal.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



import proyectoFinal.models.entities.Libro;
import proyectoFinal.models.entities.Prestamo;
import proyectoFinal.models.entities.Usuario;

public interface IUsuarioService {
List<Usuario> listar();
Page<Usuario> listar(Pageable pageable);
void save(Usuario usuario);
void delete(Long id);
Usuario findById(Long id);
Page<Usuario> findByNombre(Pageable pageable,String nombre);
//
void guardarPrestamo(Prestamo prestamo);
Libro findLibroById(Long id);
void deletePrestamo(Prestamo prestamo);
Prestamo findPrestamoById(Long id); 

}
