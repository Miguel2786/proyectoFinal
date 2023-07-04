package proyectoFinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyectoFinal.models.dao.ILibroDao;
import proyectoFinal.models.dao.IPrestamoDao;
import proyectoFinal.models.dao.IUsuarioDao;
import proyectoFinal.models.entities.Libro;
import proyectoFinal.models.entities.Prestamo;
import proyectoFinal.models.entities.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	IUsuarioDao usuarioDao;
	
	@Autowired
	IPrestamoDao prestamoDao;
	
	@Autowired
	ILibroDao libroDao;
	

	
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> listar(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findByNombre(Pageable pageable, String nombre) {
		return usuarioDao.findByNombre(pageable,nombre);
	}

	@Override
	@Transactional
	public void guardarPrestamo(Prestamo prestamo) {
		prestamoDao.save(prestamo);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Libro findLibroById(Long id) {
		return libroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void deletePrestamo(Prestamo prestamo) {
		prestamoDao.delete(prestamo);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Prestamo findPrestamoById(Long id) {
		return prestamoDao.findById(id).orElse(null);
	}

	
}
