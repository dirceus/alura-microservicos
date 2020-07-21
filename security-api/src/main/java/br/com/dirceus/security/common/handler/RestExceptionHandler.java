package br.com.dirceus.security.common.handler;

import java.util.Arrays;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.dirceus.security.common.exception.BusinessException;
import br.com.dirceus.security.common.exception.BusinessExceptionDetails;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusinessException(BusinessException businessException){
		
		
		StackTraceElement[] stack = Arrays.copyOfRange(businessException.getStackTrace(),0,5);
		
		BusinessExceptionDetails bED = 
				BusinessExceptionDetails.builder()
				.withMensagem(businessException.getMessage())
				.withDetalhes(stack)
				.withStatus(500)
				.withTimestamp(new Date().getTime())
				.build();
		
		return new ResponseEntity<>(bED,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
