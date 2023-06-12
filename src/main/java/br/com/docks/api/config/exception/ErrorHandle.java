package br.com.docks.api.config.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandle {
	
	 @ExceptionHandler(EntityNotFoundException.class)
	    public ResponseEntity<Void> Error404() {
	        return ResponseEntity.notFound().build();
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<?> Error400(MethodArgumentNotValidException ex) {
	        var erros = ex.getFieldErrors();
	        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	    }

	    public record DadosErroValidacao(String campo, String mensagem) {

	        public DadosErroValidacao(FieldError erro) {
	            this(erro.getField(), erro.getDefaultMessage());
	        }
	    }

}
