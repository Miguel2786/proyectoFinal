package proyectoFinal.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import proyectoFinal.models.entities.Libro;

public interface ILibroDao extends PagingAndSortingRepository<Libro,Long>,CrudRepository<Libro,Long> {
	
	Page<Libro> findByGenero(Pageable pageable,String genero);
	Page<Libro> findByAutor(Pageable pageable,String autor);
	
	
}
