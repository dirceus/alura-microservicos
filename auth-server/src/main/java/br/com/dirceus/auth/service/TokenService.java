package br.com.dirceus.auth.service;

import br.com.dirceus.auth.common.exception.BusinessException;
import br.com.dirceus.auth.dto.TokenInfoDTO;
import br.com.dirceus.auth.model.Usuario;

public interface TokenService {

	public String gerarToken(Usuario usuario);
	
	public Boolean validarToken(String token);
	
	public TokenInfoDTO tokenInfo(String token) throws BusinessException;
	
	public void destruirToken(String token) throws BusinessException;
	
	
	
}
