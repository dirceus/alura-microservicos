package br.com.dirceus.auth.dto;

public class LoginDTO {

	private String email;
	private String senha;
	
	LoginDTO(){
		
	}
	
	public LoginDTO(String email, String senha) {
		this.email = email;
		this.senha = senha;
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
