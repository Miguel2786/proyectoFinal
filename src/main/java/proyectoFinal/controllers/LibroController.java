package proyectoFinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import org.springframework.data.domain.Pageable;

import proyectoFinal.models.entities.Libro;
import proyectoFinal.services.ILibroService;
import proyectoFinal.paginator.PageRender;


@Controller
@RequestMapping("/libros")
public class LibroController {

	@Autowired
	ILibroService libroService;
	
	@GetMapping("/listar")
	public String listar(@RequestParam(defaultValue="0") int page,Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Libro> libros = libroService.listar(pageRequest);
		PageRender<Libro> pageRender = new PageRender<>("/libros/listar",libros);
		model.addAttribute("titulo","Listado de libros");
		model.addAttribute("libros",libros);
		model.addAttribute("page",pageRender);
		return "/libros/listar";
	}
	
	
	@GetMapping("/alta")
	public String darAlta(Model model) {
		model.addAttribute("titulo","Dar de alta a un nuevo libro");
		model.addAttribute("libro",new Libro());
		
		return "libros/form";
	}
	
	@PostMapping("/form")
	public String guardar(@Valid Libro libro,BindingResult result,Model model,RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo","Editar un libro");
			return "libros/form";
		}
		libroService.save(libro);
		flash.addFlashAttribute("success", "Libro guardado con éxito");
		return "redirect:/libros/listar";
	}
	
	@GetMapping("/baja/{id}")
	public String borrar(@PathVariable Long id,Model model,RedirectAttributes flash) {

		model.addAttribute("titulo", "Listado de libros");
		libroService.delete(id);
		model.addAttribute("libros", libroService.listar());
		flash.addFlashAttribute("warning","Libro borrado con éxito");
		return "redirect:/libros/listar";
		
	}
	
	@GetMapping("/modificar/{id}")
	public String modificar(@PathVariable (name="id")Long id,Model model) {
		model.addAttribute("titulo", "Modificar libro");
		model.addAttribute("libro",libroService.findById(id));
		return "libros/form";
	}
	
	@GetMapping("/id/{id}")
	public String listarPorId(@PathVariable Long id, Model model) {
		boolean noBarra=true;
		model.addAttribute("titulo", "Listado de libros por Id");
		model.addAttribute("libros",libroService.findById(id));
		model.addAttribute("nobarra", noBarra);
		return "libros/listar";		
	}
	
	@GetMapping("/genero/{genero}")
	public String listarPorGenero(@PathVariable String genero,@RequestParam(defaultValue="0") int page,Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Libro> libros = libroService.findByGenero(pageRequest,genero);
		PageRender<Libro> pageRender = new PageRender<>("/libros/genero/"+genero,libros);
		model.addAttribute("titulo","Listado de libros por genero");
		model.addAttribute("libros",libros);
		model.addAttribute("page",pageRender);
		return "/libros/listar";
	}
	
	@GetMapping("/autor/{autor}")
	public String listarPorAutor(@PathVariable String autor,@RequestParam(defaultValue="0") int page,Model model) {
		Pageable pageRequest = PageRequest.of(page, 5);
		Page<Libro> libros = libroService.findByAutor(pageRequest,autor);
		PageRender<Libro> pageRender = new PageRender<>("/libros/autor/"+autor,libros);
		model.addAttribute("titulo","Listado de libros por autor");
		model.addAttribute("libros",libros);
		model.addAttribute("page",pageRender);
		return "/libros/listar";
	}
}
