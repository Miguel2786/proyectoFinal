package proyectoFinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import proyectoFinal.models.entities.Usuario;
import proyectoFinal.services.IUsuarioService;
import proyectoFinal.paginator.PageRender;
@Controller
@RequestMapping("/")
public class UsuarioController {

	@Autowired
	IUsuarioService usuarioService;

	@GetMapping({"/","index",""})
	public String m1() {
		
		return "/vista/index";
	}
	@GetMapping("/usuarios/listar")
	public String listar(@RequestParam(defaultValue="0")int page,Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);//de 5 en 5 elementos por pagina
		Page<Usuario> usuarios = usuarioService.listar(pageRequest);
		PageRender<Usuario> pageRender = new PageRender<>("/usuarios/listar",usuarios);
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarios);
		model.addAttribute("page", pageRender);

		return "/usuarios/listar";
	}
	
	@GetMapping("/usuarios/alta")
	public String darAlta(Model model) {
		model.addAttribute("titulo","Dar de alta a un nuevo usuario");
		model.addAttribute("usuario", new Usuario());
		return "usuarios/form";
		
	}
	@PostMapping("/usuarios/form")
	public String guardar(@Valid Usuario usuario,BindingResult result,Model model,RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Editar un usuario");
			return "usuarios/form";
		}
		usuarioService.save(usuario);
		flash.addFlashAttribute("success", "Usuario guardado con éxito");
		return "redirect:/usuarios/listar";
	}
	//borrar y editar
	@GetMapping("/usuarios/baja/{id}")
	public String borrar(@PathVariable Long id,Model model,RedirectAttributes flash) {

		model.addAttribute("titulo", "Listado de usuarios");
		usuarioService.delete(id);
		model.addAttribute("usuarios",usuarioService.listar());
		flash.addFlashAttribute("warning", "Usuario borrado con éxito");
		return "redirect:/usuarios/listar";
	}
	
	@GetMapping("/usuarios/modificar/{id}")
	public String modificar(@PathVariable (name="id")Long id,Model model) {
		model.addAttribute("titulo", "Modificar usuario");
		model.addAttribute("usuario",usuarioService.findById(id));
		return "usuarios/form";
	}
	@GetMapping("/usuarios/id/{id}")
	public String listarPorId(@PathVariable Long id, Model model) {
		boolean noBarra=true;
		model.addAttribute("titulo", "Listado de usuarios por id");
		model.addAttribute("usuarios",usuarioService.findById(id));
		model.addAttribute("nobarra", noBarra);
		return "usuarios/listar";		
	}
	
	@GetMapping("/usuarios/nombre/{nombre}")
	public String listarPorGenero(@PathVariable String nombre,@RequestParam(defaultValue="0") int page,Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Usuario> usuarios = usuarioService.findByNombre(pageRequest,nombre);
		PageRender<Usuario> pageRender = new PageRender<>("/usuarios/nombre/"+nombre,usuarios);
		model.addAttribute("titulo","Listado de usuarios nombre");
		model.addAttribute("usuarios",usuarios);
		model.addAttribute("page",pageRender);
		return "/usuarios/listar";
	}
		
}
