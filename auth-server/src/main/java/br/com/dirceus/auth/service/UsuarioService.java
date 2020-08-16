package br.com.dirceus.auth.service;

import java.util.List;

import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;
import br.com.dirceus.meudoc.commons.exception.RepositoryException;


public interface UsuarioService {

	public Boolean emailJaCadastrado(String email);
	
	public List<UsuarioDTO> getUsuarios(Integer pagina,
										Integer tamanho,
										String ordenarPor,
										Boolean crescente)
												throws BusinessException;
	
	public UsuarioDTO getUsuario(Long id) throws BusinessException;
	
	public UsuarioDTO getUsuarioPorEmail(String email) throws BusinessException;
	
	public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDto) throws BusinessException, RepositoryException;
	
	public UsuarioDTO login(String email, String senha) throws BusinessException;

	public UsuarioDTO getUsuarioPorToken(String token) throws BusinessException;;
	
}
