package br.com.dirceus.auth.teste.integracao.controller;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dirceus.auth.service.TokenService;
import br.com.dirceus.auth.service.UsuarioService;
import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;
import br.com.dirceus.meudoc.commons.exception.ErrorDetails;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;
	@MockBean
	private UsuarioService usuarioService;

	@Autowired
	private TokenService tokenService;

	private ModelMapper modelMapper = new ModelMapper();

	@Test
	public void registrarUsuarioComValoresInvalidosDeveRetornarStatus400() throws BusinessException {

		UsuarioDTO usuarioDTO = new UsuarioDTO(null,"A","email@email.com","s",null,null);
		
		ResponseEntity<ErrorDetails> response = restTemplate.postForEntity("/usuario/registrar", usuarioDTO, ErrorDetails.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		//Assertions.assertThat(response.getBody().getErros().size()).isEqualTo(2);
	}

	

}
