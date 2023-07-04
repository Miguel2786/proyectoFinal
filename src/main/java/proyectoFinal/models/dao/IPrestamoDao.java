package proyectoFinal.models.dao;

import org.springframework.data.repository.CrudRepository;

import proyectoFinal.models.entities.Prestamo;


public interface IPrestamoDao extends CrudRepository<Prestamo, Long>{

}
