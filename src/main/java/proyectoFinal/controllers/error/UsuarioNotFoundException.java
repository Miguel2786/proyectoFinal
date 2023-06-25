package proyectoFinal.controllers.error;

import proyectoFinal.models.entities.Usuario;

public class UsuarioNotFoundException extends RuntimeException{

	public UsuarioNotFoundException(Usuario usuario) {
		super(String.format("Usuario con Id %s no encotrado o nombre %s no encontrado ", usuario.getId(),usuario.getNombre()));
	}
}
