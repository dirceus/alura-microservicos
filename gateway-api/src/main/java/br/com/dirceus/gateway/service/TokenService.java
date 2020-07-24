package br.com.dirceus.gateway.service;

import br.com.dirceus.gateway.model.Usuario;

public interface TokenService {

	public String gerarToken(Usuario usuario);
	
	public Boolean validarToken(String token);
	
	public void destruirToken(String token);
	
}
