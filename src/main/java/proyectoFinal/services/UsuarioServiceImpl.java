package proyectoFinal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	
}
