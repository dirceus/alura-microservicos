package br.com.dirceus.meudoc.domain.model.acervo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Acervo {

	@Id
	private String id;
	
	@Indexed(unique=true, sparse=true)
	private String nome;
	
	public Acervo() {
		
	}
	
	public Acervo(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
		
}
