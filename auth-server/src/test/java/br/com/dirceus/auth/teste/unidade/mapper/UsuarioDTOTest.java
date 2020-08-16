package br.com.dirceus.auth.teste.unidade.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import br.com.dirceus.auth.model.Usuario;
import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.modelmapper.MapperUtil;
import br.com.dirceus.meudoc.commons.security.Permissao;

public class UsuarioDTOTest {

	private ModelMapper modelMapper = new ModelMapper();
	
	@Test
	void deveConverterUsuarioEmUsuarioDTOComAtributosNulos() {
		Usuario usuario = new Usuario();
		UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
		
		assertEquals(usuario.getId(), dto.getId());
		assertEquals(usuario.getNome(), dto.getNome());
	}
	
	@Test
	void deveConverterUsuarioEmUsuarioDTOComTodosAtributosPreenchidos() {
		List<Permissao> permissoes = new ArrayList<>();
		permissoes.add(Permissao.USER);
		Usuario usuario = new Usuario(1L,"Nome", "email","senha", permissoes);
		
		UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
		
		assertEquals(usuario.getId(), dto.getId());
		assertEquals(usuario.getNome(), dto.getNome());
		assertEquals(usuario.getEmail(), dto.getEmail());
		assertEquals(usuario.getPermissoes().get(0), dto.getPermissoes().get(0));
	}
	
	@Test
	void deveConverterUsuarioDTOEmUsuarioComAtributosNulos() {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
		
		assertEquals(usuarioDTO.getId(), usuario.getId());
		assertEquals(usuarioDTO.getNome(), usuario.getNome());
	}
	
	@Test
	void deveConverterUsuarioDTOEmUsuarioComTodosAtributosPreenchidos() {
		List<Permissao> permissoes = new ArrayList<>();
		permissoes.add(Permissao.USER);
		UsuarioDTO usuarioDTO = new UsuarioDTO(1L,"Nome", "email","senha","token", permissoes);
		
		Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
		
		assertEquals(usuarioDTO.getId(), usuario.getId());
		assertEquals(usuarioDTO.getNome(), usuario.getNome());
		assertEquals(usuarioDTO.getEmail(), usuario.getEmail());
		assertEquals(usuarioDTO.getPermissoes().get(0), usuario.getPermissoes().get(0));
	}
	
	@Test
	void deveConverterListaDeUsuariosEmUmaListaUsuariosDTO() {
		/* INÍCIO da criação da lista de usuarios */
		List<Permissao> permissoes = new ArrayList<>();
		permissoes.add(Permissao.USER);
		Usuario usuario = new Usuario(1L,"Nome", "email","senha", permissoes);
		
		List<Permissao> permissoes2 = new ArrayList<>();
		permissoes2.add(Permissao.ADMIN);
		Usuario usuario2 = new Usuario(2L,"Nome2", "email2","senha2", permissoes2);
		
		List<Usuario> usuarios = new ArrayList<>();
		usuarios.add(usuario);
		usuarios.add(usuario2);
		/* FIM da criação da lista de usuários */
		
		List<UsuarioDTO> usuariosDTO = MapperUtil.mapList(usuarios, UsuarioDTO.class);
		
		assertEquals(usuarios.size(),
					usuariosDTO.size());
		assertEquals(usuarios.get(0).getNome(),
					usuariosDTO.get(0).getNome());
		assertEquals(usuarios.get(1).getPermissoes().get(0),
					usuariosDTO.get(1).getPermissoes().get(0));
	}
	
	
	
}
