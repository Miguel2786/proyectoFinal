package proyectoFinal.controllers.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorsController {

	@GetMapping("/template-no-existe")
	public String m1() {
		return "template-no-existe";
	}
	
	@GetMapping("/null-pointer")
	public String m2() {
		String s = null;
		s.length();
		
		return "template-no existe";
	}
}
