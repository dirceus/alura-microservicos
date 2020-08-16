package br.com.dirceus.auth.teste.integracao.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dirceus.auth.dto.LoginDTO;
import br.com.dirceus.auth.model.Usuario;
import br.com.dirceus.auth.service.TokenService;
import br.com.dirceus.auth.service.UsuarioService;
import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;
import br.com.dirceus.meudoc.commons.security.Permissao;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AutenticacaoControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private UsuarioService usuarioService;

	@Autowired
	private TokenService tokenService;

	private ModelMapper modelMapper = new ModelMapper();

	@Test
	public void requisicaoParaPathInexistenteDeveRetornarStatus404() throws BusinessException {

		ResponseEntity<Object> response = restTemplate.postForEntity("/path_inexistente", null, Object.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void autenticarUsuarioSemParametroDeveRetornarStatus400() throws BusinessException {

		ResponseEntity<UsuarioDTO> response = restTemplate.postForEntity("/auth", null, UsuarioDTO.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void autenticarUsuarioQuandoEmailESenhaCorretosDeveRetornarStatus200() {

		List<Permissao> permissoes = new ArrayList<Permissao>();
		permissoes.add(Permissao.USER);
		Usuario usuario = new Usuario(1L, "Dirceu", "dirceu.crb@gmail.com", "123456", permissoes);
		UsuarioDTO usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
		try {
			BDDMockito.when(usuarioService.login("dirceu.crb@gmail.com", "123456")).thenReturn(usuarioDTO);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		LoginDTO loginDTO = new LoginDTO("dirceu.crb@gmail.com", "123456");

		ResponseEntity<UsuarioDTO> response = restTemplate.postForEntity("/auth", loginDTO, UsuarioDTO.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		Assertions.assertThat(response.getBody().getToken()).isNotNull();
		Assertions.assertThat(response.getBody().getId()).isEqualTo(1L);
	}

	@Test
	public void autenticarUsuarioQuandoEmailESenhaIncorretosDeveRetornarStatus500() throws BusinessException {

		BDDMockito.when(usuarioService.login("dirceu.crb@gmail.com", "abcdef"))
				.thenThrow(new BusinessException("Usuário e senha inválidos."));

		LoginDTO loginDTO = new LoginDTO("dirceu.crb@gmail.com", "abcdef");

		ResponseEntity<UsuarioDTO> response = restTemplate.postForEntity("/auth", loginDTO, UsuarioDTO.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(500);

	}

	@Test
	public void obtemUsuarioQuandoInformadoTokenValido() throws BusinessException {

		UsuarioDTO usuarioDTO = new UsuarioDTO(1L, "Dirceu", "dirceu.crb@gmail.com");

		String token = tokenService.gerarToken(usuarioDTO);

		try {
			BDDMockito.when(usuarioService.getUsuarioPorToken(token)).thenReturn(usuarioDTO);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		ResponseEntity<UsuarioDTO> response = restTemplate.postForEntity("/auth/usuario", token, UsuarioDTO.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		Assertions.assertThat(response.getBody().getId()).isEqualTo(usuarioDTO.getId());
		Assertions.assertThat(response.getBody().getEmail()).isEqualTo(usuarioDTO.getEmail());
	}

}
