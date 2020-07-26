package br.com.dirceus.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dirceus.auth.common.exception.BusinessException;
import br.com.dirceus.auth.common.exception.RepositoryException;
import br.com.dirceus.auth.model.Usuario;
import br.com.dirceus.auth.service.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/registrar")
	public Usuario registrar(@RequestBody Usuario usuario) throws BusinessException, RepositoryException {
		return this.usuarioService.registrarUsuario(usuario);
	}
	
	@GetMapping("/existe-email/{email}")
	public Boolean emailRegistrado(@PathVariable String email) {
		Usuario usuario = this.usuarioService.getUsuarioPorEmail(email);
		return usuario != null ? true : false;
	}
	
}
