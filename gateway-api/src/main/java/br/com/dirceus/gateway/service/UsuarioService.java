package br.com.dirceus.gateway.service;

import java.util.List;

import br.com.dirceus.gateway.common.exception.BusinessException;
import br.com.dirceus.gateway.model.Usuario;


public interface UsuarioService {

	public List<Usuario> getUsuarios();
	
	public Usuario getUsuarioPorEmail(String email);
	
	public Usuario registrarUsuario(Usuario usuario) throws BusinessException;
	
	public Usuario login(String email, String senha) throws BusinessException;
	
}
