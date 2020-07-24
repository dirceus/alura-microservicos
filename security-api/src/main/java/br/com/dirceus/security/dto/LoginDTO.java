package br.com.dirceus.security.dto;

public class LoginDTO {

	private String email;
	private String senha;
	
	LoginDTO(){
		
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
