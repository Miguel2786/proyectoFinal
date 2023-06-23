package proyectoFinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proyectoFinal.services.IUsuarioService;

@Controller
@RequestMapping({"/","index",""})
public class UsuarioController {

	@Autowired
	IUsuarioService usuarioService;
	
	@GetMapping("/usuarios/listar")
	public String listar (Model model) {
		
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarioService.listar());
		
		return "/usuario/listar";
	}
}
