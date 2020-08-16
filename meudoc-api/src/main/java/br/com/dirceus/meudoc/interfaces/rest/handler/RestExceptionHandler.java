package br.com.dirceus.meudoc.interfaces.rest.handler;

import java.util.Arrays;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
		
		String mensagem = "Erro Interno (500)";
		if(exception instanceof BusinessException) {
			mensagem = "Erro de Regra de Negócio (500)";
		}else if(exception instanceof RepositoryException){
			mensagem = "Erro durante acesso a Base de Dados (500)";
		}
		
		ErrorDetails details = 
				ErrorDetails.builder()
				.withMensagem(mensagem)
				.withErros(Arrays.asList(exception.getMessage()))
				.withDetalhes(Arrays.copyOfRange(exception.getStackTrace(),0,5))
				.withStatus(500)
				.withData(new Date())
				.build();
		
		return new ResponseEntity<>(details,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(PermissionDeniedException.class)
	public ResponseEntity<ErrorDetails> handlePermissionDeniedException(PermissionDeniedException permissionException){
		
		ErrorDetails details = 
				ErrorDetails.builder()
				.withMensagem("Error de Permissão (401)")
				.withErros(Arrays.asList(permissionException.getMessage()))
				.withDetalhes(Arrays.copyOfRange(permissionException.getStackTrace(),0,5))
				.withStatus(401)
				.withData(new Date())
				.build();
		
		return new ResponseEntity<>(details,HttpStatus.UNAUTHORIZED);
	}
	
	
	
}
