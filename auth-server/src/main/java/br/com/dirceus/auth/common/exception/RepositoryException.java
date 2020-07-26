package br.com.dirceus.auth.common.exception;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public RepositoryException(){
		super("Ocorreu erro durante o acesso a base de dados");
	}
	
	public RepositoryException(String msg){
		super(msg);
	}
	
	public RepositoryException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	
}
