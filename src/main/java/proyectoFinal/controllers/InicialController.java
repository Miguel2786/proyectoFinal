package proyectoFinal.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class InicialController {
	
	
		@GetMapping("menu")
		public String m1() {
			
			return "/vista/index";
		}
}
