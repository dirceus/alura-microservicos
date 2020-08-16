package br.com.dirceus.auth.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import br.com.dirceus.meudoc.commons.security.Permissao;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@Size(min=3, max = 100)
	private String nome;
	@Column(unique = true, nullable = false)
	@Email
	private String email;
	@Column(nullable = false)
	private String senhaEncriptada;
	
	@ElementCollection(targetClass = Permissao.class,fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissoes", joinColumns = @JoinColumn(name = "userID"))
	@Column(name = "permissao", nullable = false)
	@Enumerated(EnumType.STRING)
	private List<Permissao> permissoes;
	
	public Usuario() {
		
	}
	
	public Usuario(Long id, String nome, String email, 
				   String senhaEncriptada, List<Permissao> permissoes) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senhaEncriptada = senhaEncriptada;
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
	public String getSenhaEncriptada() {
		return senhaEncriptada;
	}
	public void setSenhaEncriptada(String senha) {
		this.senhaEncriptada = senha;
	}
	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	
	
	
	
}
