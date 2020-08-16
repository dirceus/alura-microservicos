package br.com.dirceus.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dirceus.auth.dto.LoginDTO;
import br.com.dirceus.auth.service.TokenService;
import br.com.dirceus.auth.service.UsuarioService;
import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	public UsuarioDTO autenticar(@RequestBody LoginDTO loginDto) throws BusinessException {

		UsuarioDTO usuarioDTO = usuarioService.login(loginDto.getEmail(), loginDto.getSenha());
		String token = tokenService.gerarToken(usuarioDTO);
		usuarioDTO.setToken(token);

		return usuarioDTO;

	}

	@PostMapping("/usuario")
	public UsuarioDTO getUsuarioPorToken(@RequestBody String token) throws BusinessException {
		return usuarioService.getUsuarioPorToken(token);
	}

}
