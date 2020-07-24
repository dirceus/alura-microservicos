package br.com.dirceus.storage.model;

/**
 * @author dirceu
 *
 */
public class Documento {

	public Long id;
	public String aplicacao;
	public String referencia;
	public String metadados;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAplicacao() {
		return aplicacao;
	}
	public void setAplicacao(String aplicacao) {
		this.aplicacao = aplicacao;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getMetadados() {
		return metadados;
	}
	public void setMetadados(String metadados) {
		this.metadados = metadados;
	}
	
	public String getUrl() {
		return "/download/"+this.id;
	}
	
	
}
