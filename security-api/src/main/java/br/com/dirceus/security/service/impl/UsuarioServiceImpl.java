package br.com.dirceus.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.dirceus.security.common.exception.BusinessException;
import br.com.dirceus.security.model.Usuario;
import br.com.dirceus.security.repository.UsuarioRepository;
import br.com.dirceus.security.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl() {
	}
	
	@Override
	public List<Usuario> getUsuarios() {
		return Lists.newArrayList(this.usuarioRepository.findAll());
	}

	@Override
	public Usuario getUsuarioPorEmail(String email) {
		return this.usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario registrarUsuario(Usuario usuario) throws BusinessException {
		
		//verifica se email já está cadastrado.
		Usuario usuario_armazenado = this.getUsuarioPorEmail(usuario.getEmail());
		if(usuario_armazenado == null) {
			return this.usuarioRepository.save(usuario);
		} else {
			throw new BusinessException("Email já cadastrado");
		}
		
		
	}

	@Override
	public Usuario login(String email, String senha) throws BusinessException {
		
		Usuario usuario = this.getUsuarioPorEmail(email);
		
		if(usuario == null) {
			throw new BusinessException("Email não encontrado.");
		}
		if(!usuario.getSenha().equals(senha)) {
			throw new BusinessException("Usuário e senha inválidos.");
		}
		
		return usuario;
	}
	

}
