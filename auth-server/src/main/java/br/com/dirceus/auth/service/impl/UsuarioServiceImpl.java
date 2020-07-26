package br.com.dirceus.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.dirceus.auth.common.exception.BusinessException;
import br.com.dirceus.auth.common.exception.RepositoryException;
import br.com.dirceus.auth.dto.TokenInfoDTO;
import br.com.dirceus.auth.model.Permissao;
import br.com.dirceus.auth.model.Usuario;
import br.com.dirceus.auth.repository.UsuarioRepository;
import br.com.dirceus.auth.service.TokenService;
import br.com.dirceus.auth.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
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
	public Usuario registrarUsuario(Usuario usuario) throws BusinessException, RepositoryException {
		
		//verifica se email já está cadastrado.
		Usuario usuario_armazenado = this.getUsuarioPorEmail(usuario.getEmail());
		
		if(usuario_armazenado == null) {
			
			//adicionar as credenciais do usuário
			List<Permissao> permissoes = new ArrayList<Permissao>();
			permissoes.add(Permissao.USER);
			usuario.setPermissoes(permissoes);
			//armazena o usuário no banco de dados
			try {
				return this.usuarioRepository.save(usuario);
			}catch (Exception e) {
				throw new RepositoryException("Erro ao salvar o usuário no banco de dados");
			}
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

	@Override
	public Usuario getUsuarioPorToken(String token) throws BusinessException {
		TokenInfoDTO tokenInfo = tokenService.tokenInfo(token);
		return getUsuarioPorEmail(tokenInfo.getEmail());
	}
	

}
