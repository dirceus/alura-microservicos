package br.com.dirceus.auth.controller.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.dirceus.meudoc.commons.exception.BusinessException;
import br.com.dirceus.meudoc.commons.exception.ErrorDetails;
import br.com.dirceus.meudoc.commons.exception.PermissionDeniedException;
import br.com.dirceus.meudoc.commons.exception.RepositoryException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({BusinessException.class, RepositoryException.class})
	public ResponseEntity<ErrorDetails> handleException(Exception exception){
		
		StackTraceElement[] stack = Arrays.copyOfRange(exception.getStackTrace(),0,5);
		
		String mensagem = "Erro Interno (500)";
		if(exception instanceof BusinessException) {
			mensagem = "Erro de Regra de Negócio (500)";
		}else if(exception instanceof RepositoryException){
			mensagem = "Erro durante acesso a Base de Dados (500)";
		}
		
		ErrorDetails exDetails = 
				ErrorDetails.builder()
				.withMensagem(mensagem)
				.withErros(Arrays.asList(exception.getMessage()))
				.withDetalhes(stack)
				.withStatus(500)
				.withData(new Date())
				.build();
		
		return new ResponseEntity<>(exDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(PermissionDeniedException.class)
	public ResponseEntity<ErrorDetails> handlePermissionDeniedException(PermissionDeniedException permissionException){
		
		
		StackTraceElement[] stack = Arrays.copyOfRange(permissionException.getStackTrace(),0,5);
		
		ErrorDetails pED = 
				ErrorDetails.builder()
				.withMensagem("Error de Permissão (401)")
				.withErros(Arrays.asList(permissionException.getMessage()))
				.withDetalhes(stack)
				.withStatus(401)
				.withData(new Date())
				.build();
		
		return new ResponseEntity<>(pED,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		
		
		StackTraceElement[] stack = Arrays.copyOfRange(ex.getStackTrace(),0,5);
		
		List<String> errors = new ArrayList<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        
	        String errorMessage = error.getDefaultMessage();
	        errors.add(StringUtils.capitalize(fieldName)+ ": "+ errorMessage);
	    });
	   
		
		ErrorDetails pED = 
				ErrorDetails.builder()
				.withMensagem("Error de Validação (400)")
				.withErros(errors)
				.withDetalhes(stack)
				.withStatus(400)
				.withData(new Date())
				.build();
		
		return new ResponseEntity<>(pED,HttpStatus.BAD_REQUEST);
	}
	
}
