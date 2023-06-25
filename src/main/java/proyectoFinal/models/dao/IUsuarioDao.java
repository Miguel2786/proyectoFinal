package proyectoFinal.models.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import proyectoFinal.models.entities.Usuario;


public interface IUsuarioDao extends  PagingAndSortingRepository<Usuario,Long>,CrudRepository<Usuario, Long> {
	Page<Usuario> findByNombre(Pageable pageable,String nombre);
}
