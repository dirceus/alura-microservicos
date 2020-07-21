package br.com.dirceus.security.service;

import br.com.dirceus.security.model.Usuario;

public interface TokenService {

	public String gerarToken(Usuario usuario);
	
	public Boolean validarToken(String token);
	
	public void destruirToken(String token);
	
}
