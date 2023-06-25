package proyectoFinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyectoFinal.models.dao.IUsuarioDao;
import proyectoFinal.models.entities.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	IUsuarioDao usuarioDao;
	
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
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	public Page<Usuario> findByNombre(Pageable pageable, String nombre) {
		return usuarioDao.findByNombre(pageable,nombre);
	}

	
}
