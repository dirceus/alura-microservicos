package br.com.dirceus.auth.dto;

import br.com.dirceus.auth.model.Usuario;

public class UsuarioAutenticadoDTO {

	private Long id;
	private String nome;
	private String email;
	private String token;
	
	public UsuarioAutenticadoDTO() {}
	
	public UsuarioAutenticadoDTO(Long id, String nome, String email, String token) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.token = token;
				
		
	}
	
	public static UsuarioAutenticadoDTO toDTO(Usuario usuario, String token) {
		return new UsuarioAutenticadoDTO(usuario.getId(),
				usuario.getNome(),
				usuario.getEmail(),
				token);
		
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
