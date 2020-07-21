package br.com.dirceus.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.dirceus.security.common.exception.BusinessException;
import br.com.dirceus.security.model.Usuario;
import br.com.dirceus.security.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/registrar")
	public Usuario registrar(@RequestBody Usuario usuario) throws BusinessException {
		return this.usuarioService.registrarUsuario(usuario);
	}
		
}
