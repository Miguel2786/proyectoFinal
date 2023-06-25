package proyectoFinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyectoFinal.models.dao.ILibroDao;
import proyectoFinal.models.entities.Libro;

@Service
public class LibroServiceImp implements ILibroService{

	@Autowired
	ILibroDao libroDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Libro> listar() {
		return (List<Libro>) libroDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Libro> listar(Pageable pageable) {
		return libroDao.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Libro libro) {
		libroDao.save(libro);
		
	}

	@Override
	@Transactional
	public void delete(Long id) {
		libroDao.deleteById(id);
	}

	@Override
	@Transactional
	public Libro findById(Long id) {
		return libroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Libro> findByGenero(Pageable pageable, String genero) {
		return libroDao.findByGenero(pageable, genero);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Libro> findByAutor(Pageable pageable, String autor) {
		return libroDao.findByAutor(pageable, autor);
	}
}
