package br.com.dirceus.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dirceus.auth.common.exception.BusinessException;
import br.com.dirceus.auth.dto.LoginDTO;
import br.com.dirceus.auth.dto.UsuarioAutenticadoDTO;
import br.com.dirceus.auth.model.Usuario;
import br.com.dirceus.auth.service.TokenService;
import br.com.dirceus.auth.service.UsuarioService;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public UsuarioAutenticadoDTO autenticar(@RequestBody LoginDTO loginDto) throws BusinessException {
	
		Usuario usuario =  usuarioService.login(loginDto.getEmail(), loginDto.getSenha());
		String token = tokenService.gerarToken(usuario);
		
		return UsuarioAutenticadoDTO.toDTO(usuario, token);
		
	}
	
	@PostMapping("/valida_token")
	public Boolean validaToken(String token) {
		return tokenService.validarToken(token);
	}
	
	@PostMapping("/usuario")
	public Usuario getUsuarioPorToken(String token) throws BusinessException{
		return usuarioService.getUsuarioPorToken(token);
	}
	
	
	
	

	
}
