package br.com.dirceus.auth.service;

import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;

public interface TokenService {

	public String gerarToken(UsuarioDTO usuarioDTO);
	
	public UsuarioDTO getUsuarioDoToken(String token) throws BusinessException;
	
	public void destruirToken(String token) throws BusinessException;
	
	
	
}
