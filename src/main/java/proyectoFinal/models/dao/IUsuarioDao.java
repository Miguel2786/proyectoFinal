package proyectoFinal.models.dao;

import org.springframework.data.repository.CrudRepository;

import proyectoFinal.models.entities.Usuario;


public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

}
