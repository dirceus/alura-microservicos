package br.com.dirceus.auth.teste.unidade.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dirceus.auth.model.Usuario;
import br.com.dirceus.auth.repository.UsuarioRepository;
import br.com.dirceus.auth.service.UsuarioService;
import br.com.dirceus.auth.service.impl.UsuarioServiceImpl;
import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;
import br.com.dirceus.meudoc.commons.exception.RepositoryException;

@RunWith(SpringRunner.class)
public class UsuarioServiceTests {

	@Mock
	UsuarioRepository usuarioRepository;
	
	@InjectMocks
    private UsuarioService usuarioService = new UsuarioServiceImpl(new ModelMapper());
	
	@Before
	public void setUp() {
	    Usuario alex = new Usuario(1L,"Alex","alex@email.com", null,null);
	    Usuario paulo = new Usuario(2L,"Paulo","paulo@email.com",null,null);
	    Usuario augusto = new Usuario(3L,"Augusto","augusto@email.com",null,null);
	    
	    List<Usuario> usuarios = new ArrayList<>();
	    usuarios.add(alex);
	    usuarios.add(paulo);
	    usuarios.add(augusto);
	 
	    Mockito.when(usuarioRepository.findByEmail("alex@email.com"))
	      .thenReturn(alex);
	    
	    Mockito.when(usuarioRepository.findByEmail("joao@email.com"))
	      .thenReturn(null);
	    
	    Mockito.when(usuarioRepository.findAll(Mockito.any(Pageable.class)))
	      .thenReturn(new PageImpl<>(usuarios));
	    
	    
	    Usuario joaoSalvo = new Usuario();
	    joaoSalvo.setId(4L);
	    joaoSalvo.setNome("João");
	    joaoSalvo.setEmail("joao@email.com");
	    
	    Mockito.when(usuarioRepository.save(Mockito.any(Usuario.class))).thenAnswer(
	    		new Answer<Usuario>(){
	                @Override
	                public Usuario answer(InvocationOnMock invocation){
	                	Usuario usuario = invocation.getArgument(0, Usuario.class);
	                	if(usuario.getEmail().equals("joao@email.com")) {
	                		return joaoSalvo;
	                	}
	                	return null;
	                }});
	}
	
	@Test
	public void emailJaCadastrado_QuandoInformoUmEmailCadastradoDeveRetornarTrue() {

		Boolean resultado = usuarioService.emailJaCadastrado("alex@email.com");
		
		Assertions.assertThat(resultado).isEqualTo(Boolean.TRUE);
	}
	
	@Test
	public void usuarioPorEmail_QuandoInformadoUmEmailCadastradoDeveRetornarUmUsuario() throws BusinessException {
		UsuarioDTO usuarioDTO = usuarioService.getUsuarioPorEmail("alex@email.com");
		Assertions.assertThat(usuarioDTO).isNotNull();
		Assertions.assertThat(usuarioDTO.getId()).isEqualTo(1L);
	}
	
	@Test(expected = BusinessException.class)
	public void usuarioPorEmail_QuandoInformadoUmEmailNaoCadastradoDeveSerLancadaUmaBusinessException() throws BusinessException {
		usuarioService.getUsuarioPorEmail("joao@email.com");
	}
	
	@Test
	public void getUsuarios_RetornaTodosOsUsuariosCadastrados() throws BusinessException {
		List<UsuarioDTO> usuariosDTO = usuarioService.getUsuarios(null, null, null, null);
		Assertions.assertThat(usuariosDTO.size()).isEqualTo(3);
	}
	
	@Test
	public void registraUsuarioQuandoInformadoDadosValidos() throws BusinessException, RepositoryException {
		 UsuarioDTO joaoDTO = new UsuarioDTO(null, "João", "joao@email.com", "senha", null, null);
		 UsuarioDTO dtoSalvo = usuarioService.registrarUsuario(joaoDTO);
		 Assertions.assertThat(dtoSalvo.getId()).isEqualTo(4L);
	}
}
