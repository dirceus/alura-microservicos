package br.com.dirceus.auth.common.exception;

public class PermissionDeniedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PermissionDeniedException(){
		super("Não autorizado");
	}
	
	public PermissionDeniedException(String msg){
		super(msg);
	}
	
	public PermissionDeniedException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
