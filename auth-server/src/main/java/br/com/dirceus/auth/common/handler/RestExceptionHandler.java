package br.com.dirceus.auth.common.handler;

import java.util.Arrays;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.dirceus.auth.common.exception.BusinessException;
import br.com.dirceus.auth.common.exception.ExceptionDetails;
import br.com.dirceus.auth.common.exception.PermissionDeniedException;
import br.com.dirceus.auth.common.exception.RepositoryException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler({BusinessException.class, RepositoryException.class})
	public ResponseEntity<?> handleException(Exception exception){
		
		
		StackTraceElement[] stack = Arrays.copyOfRange(exception.getStackTrace(),0,5);
		
		ExceptionDetails exDetails = 
				ExceptionDetails.builder()
				.withMensagem(exception.getMessage())
				.withDetalhes(stack)
				.withStatus(500)
				.withTimestamp(new Date().getTime())
				.build();
		
		return new ResponseEntity<>(exDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(PermissionDeniedException.class)
	public ResponseEntity<?> handlePermissionDeniedException(PermissionDeniedException permissionException){
		
		
		StackTraceElement[] stack = Arrays.copyOfRange(permissionException.getStackTrace(),0,5);
		
		ExceptionDetails pED = 
				ExceptionDetails.builder()
				.withMensagem(permissionException.getMessage())
				.withDetalhes(stack)
				.withStatus(401)
				.withTimestamp(new Date().getTime())
				.build();
		
		return new ResponseEntity<>(pED,HttpStatus.UNAUTHORIZED);
	}
	
	
	
}
