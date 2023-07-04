package proyectoFinal.controllers;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import proyectoFinal.models.entities.Libro;

import proyectoFinal.models.entities.LineaPrestamo;

import proyectoFinal.models.entities.Usuario;
import proyectoFinal.models.entities.Prestamo;
import proyectoFinal.services.ILibroService;
import proyectoFinal.services.IUsuarioService;

@Controller
@RequestMapping("/prestamo")
// el objeto pedido y sus relaciones estará activo en la sesión mientras no se
// cierre
@SessionAttributes("prestamo") // mismo nombre atributo que se pasa a la vista
public class PrestamoController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ILibroService libroService;

	@GetMapping("/form/{prestamoId}")
	public String crear(@PathVariable("prestamoId") Long prestamoId, Map<String, Object> model,
			RedirectAttributes flash) {

		Usuario usuario = usuarioService.findById(prestamoId);

		if (usuario == null) {
			flash.addFlashAttribute("Error", "La tienda no existe en la base de datos");
			return "redirect:/listar";
		}

		Prestamo prestamo = new Prestamo();
		prestamo.setUsuario(usuario);
		model.put("prestamo", prestamo);
		model.put("titulo", "Creación de prestamo del libro");
		return "prestamo/form";
	}

	// url que usara el js para autocompletar
	@GetMapping(value = "/buscar-libros/{text}", produces = { "application/json" })
	public @ResponseBody List<Libro> cargarLibros(@PathVariable String text) {
		return libroService.findByAutorText(text);
	}

	// Hay que recibir los datos de cada input del form.html
	@PostMapping("/form")
	public String guardar(Prestamo prestamo, @RequestParam(name = "item_id[]", required = false) Long[] itemId,
			// @RequestParam(name = "cantidad[]", required = false) Integer[] cantidad,
			RedirectAttributes flash, SessionStatus status) {

		for (int i = 0; i < itemId.length; i++) {
			Libro libro = usuarioService.findLibroById(itemId[i]);

			LineaPrestamo linea = new LineaPrestamo();
			
			linea.setLibro(libro);
			prestamo.addLineaPestramo(linea);

		}

		usuarioService.guardarPrestamo(prestamo);
		status.setComplete();

		flash.addFlashAttribute("success", "Prestamo creado con éxito!");

		// Para mostrar detalle de tienda mostrando estado de sus pedidos
		return "redirect:/usuarios/ver/" + prestamo.getUsuario().getId();
	}

}
