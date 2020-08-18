package ComicWebApplication.application.exceptionHandlers;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class ComicReponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler({EntityNotFoundException.class, IllegalArgumentException.class})
	public final ResponseEntity<Object> handleComicNotFoundException(Exception ex, WebRequest request){
			return new ResponseEntity("400 BAD_REQUEST",HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> defaultException(Exception ex, WebRequest request){
			return new ResponseEntity("500 INTERNAL_SERVER_ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
