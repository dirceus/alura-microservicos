package br.com.dirceus.meudoc.commons.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.dirceus.meudoc.commons.security.Permissao;

public class UsuarioDTO {

	private Long id;
	@NotBlank
	@Size(min=3,max=100, message = "deve ter entre 3 e 10 caracteres")
	private String nome;
	@NotBlank
	@Email(message="deve ser válido")
	private String email;
	private String token;
	@NotBlank
	@Size(min=6,max=14, message = "deve ter entre 6 e 14 caracteres")
	private String senha;
	private List<Permissao> permissoes;
	
	public UsuarioDTO() {}
	
	public UsuarioDTO(Long id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	public UsuarioDTO(Long id, String nome, String email, String senha, String token, List<Permissao> permissoes) {
		this(id, nome, email);
		this.senha = senha;
		this.token = token;
		this.permissoes = permissoes;
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
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	
}
