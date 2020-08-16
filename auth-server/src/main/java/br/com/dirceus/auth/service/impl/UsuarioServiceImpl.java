package br.com.dirceus.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.dirceus.auth.model.Usuario;
import br.com.dirceus.auth.repository.UsuarioRepository;
import br.com.dirceus.auth.service.TokenService;
import br.com.dirceus.auth.service.UsuarioService;
import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;
import br.com.dirceus.meudoc.commons.exception.RepositoryException;
import br.com.dirceus.meudoc.commons.modelmapper.MapperUtil;
import br.com.dirceus.meudoc.commons.security.Permissao;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private ModelMapper modelMapper;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private Integer tamanhoPaginaPadrao = 20;

	public UsuarioServiceImpl() {
	}
	
	public UsuarioServiceImpl(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public List<UsuarioDTO> getUsuarios(Integer pagina,
										Integer tamanho,
										String ordenarPor,
										Boolean crescente)
												throws BusinessException {
		
		if(tamanho == null) tamanho = this.tamanhoPaginaPadrao;
		if(pagina == null) pagina = 0;
		if(ordenarPor == null) ordenarPor = "nome";
		if(crescente == null) crescente = true;
		
		try {
			Class<Usuario> clazz = Usuario.class;
			clazz.getDeclaredField(ordenarPor);
		} catch (Exception e) {
			throw new BusinessException("Atributo "+ordenarPor+" não existe na Entidade Usuário.");
		} 
		
		
		Sort sort = (crescente) ? Sort.by(ordenarPor) : Sort.by(ordenarPor).descending();
		
		
		Pageable page = PageRequest.of(pagina, tamanho, sort);
		
		
		List<Usuario> usuarios = Lists.newArrayList(this.usuarioRepository.findAll(page));
		
		return MapperUtil.mapList(usuarios, UsuarioDTO.class);
	}

	@Override
	public UsuarioDTO getUsuarioPorEmail(String email) throws BusinessException {
		Usuario usuario = this.usuarioRepository.findByEmail(email);
		if (usuario != null) {
			return modelMapper.map(usuario, UsuarioDTO.class);
		} else {
			throw new BusinessException("Usuário não encontrado");
		}
	}
	
	@Override
	public UsuarioDTO getUsuario(Long id) throws BusinessException {
		Optional<Usuario> usuario = this.usuarioRepository.findById(id);
		if (usuario.isPresent()) {
			return modelMapper.map(usuario.get(), UsuarioDTO.class);
		} else {
			throw new BusinessException("Usuário não encontrado");
		}
	}

	public Boolean emailJaCadastrado(String email) {

		try {
			this.getUsuarioPorEmail(email);
			return true;
		} catch (BusinessException e) {
			return false;
		}

	}

	@Override
	public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDto) throws BusinessException, RepositoryException {

		if (emailJaCadastrado(usuarioDto.getEmail()).equals(Boolean.TRUE)) {
			throw new BusinessException("Email já cadastrado");
		} else {	
			Usuario usuario = modelMapper.map(usuarioDto, Usuario.class);
			usuario.setSenhaEncriptada(passwordEncoder.encode(usuarioDto.getSenha()));
			// adicionar as credenciais do usuário
			List<Permissao> permissoes = new ArrayList<>();
			permissoes.add(Permissao.USER);
			usuario.setPermissoes(permissoes);
			try {
				// armazena o usuário no banco de dados
				return modelMapper.map(this.usuarioRepository.save(usuario), UsuarioDTO.class);
			} catch (Exception e) {
				throw new RepositoryException("Erro ao salvar o usuário no banco de dados");
			}
		} 
	}

	@Override
	public UsuarioDTO login(String email, String senha) throws BusinessException {

		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new BusinessException("Email não cadastrado.");
		} else {
			if (passwordEncoder.matches(senha, usuario.getSenhaEncriptada())) {
				return modelMapper.map(usuario, UsuarioDTO.class);
			}
		}
		throw new BusinessException("Usuário e senha inválidos.");
	}

	@Override
	public UsuarioDTO getUsuarioPorToken(String token) throws BusinessException {
		UsuarioDTO tokenInfo = tokenService.getUsuarioDoToken(token);
		return modelMapper.map(getUsuarioPorEmail(tokenInfo.getEmail()), UsuarioDTO.class);
	}

	

}
