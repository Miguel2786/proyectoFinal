package proyectoFinal.controllers.error;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerController {

	
	@ExceptionHandler(NullPointerException.class)
	public String templateInputError(NullPointerException ex,Model model) {
		model.addAttribute("error", "Error null pointer");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("status",HttpStatus.INTERNAL_SERVER_ERROR.value());
		model.addAttribute("timestamp", LocalDate.now());
		return "error/internal-error";
	}
	@ExceptionHandler(UsuarioNotFoundException.class)
	public String idNotFoundException(UsuarioNotFoundException ex,Model model) {
		model.addAttribute("error", "Id o nombre no encontrado");
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("timestamp", LocalDate.now());
		return "error/id";
	}
}
