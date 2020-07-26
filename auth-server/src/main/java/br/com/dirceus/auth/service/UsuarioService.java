package br.com.dirceus.auth.service;

import java.util.List;

import br.com.dirceus.auth.common.exception.BusinessException;
import br.com.dirceus.auth.common.exception.RepositoryException;
import br.com.dirceus.auth.model.Usuario;


public interface UsuarioService {

	public List<Usuario> getUsuarios();
	
	public Usuario getUsuarioPorEmail(String email);
	
	public Usuario registrarUsuario(Usuario usuario) throws BusinessException, RepositoryException;
	
	public Usuario login(String email, String senha) throws BusinessException;

	public Usuario getUsuarioPorToken(String token) throws BusinessException;;
	
}
