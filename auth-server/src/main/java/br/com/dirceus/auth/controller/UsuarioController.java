package br.com.dirceus.auth.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dirceus.auth.service.UsuarioService;
import br.com.dirceus.meudoc.commons.dto.UsuarioDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;
import br.com.dirceus.meudoc.commons.exception.RepositoryException;
import br.com.dirceus.meudoc.commons.security.Permissao;
import br.com.dirceus.meudoc.commons.security.PermissaoRequerida;

@RestController
@CrossOrigin
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/registrar")
	public UsuarioDTO registrar(@Valid @RequestBody UsuarioDTO usuario) throws BusinessException, RepositoryException {
		return this.usuarioService.registrarUsuario(usuario);
	}
	
	@GetMapping("/existe-email/{email}")
	public Boolean emailRegistrado(@PathVariable String email) {
		return this.usuarioService.emailJaCadastrado(email);
	}
	
	/*@GetMapping("/lista/{pagina}")
	@PermissaoRequerida(Permissao.USER)
	public List<UsuarioDTO> obterUsuarios(@PathVariable Integer pagina) {
		return this.usuarioService.getUsuarios(pagina, null);
	}*/
	
	@GetMapping(value = {"/lista",
						 "/lista/{pagina}",
						 "/lista/{pagina}/{tamanho}",
					     "/lista/{pagina}/{tamanho}/{ordenarPor}",
					     "/lista/{pagina}/{tamanho}/{ordenarPor}/{crescente}" 
						})
	@PermissaoRequerida(Permissao.USER)
	public List<UsuarioDTO> obterUsuarios(@PathVariable Optional<Integer>  pagina,
										  @PathVariable Optional<Integer>  tamanho,
										  @PathVariable Optional<String>  ordenarPor,
										  @PathVariable Optional<Boolean>  crescente) 
												  throws BusinessException{
		
		Integer paramPagina = pagina.isPresent() ? pagina.get() : null;
		Integer paramTamanho = tamanho.isPresent() ? tamanho.get() : null;
		String paramOrdenarPor  = ordenarPor.isPresent() ? ordenarPor.get() : null;
		Boolean paramCrescente  = crescente.isPresent() ? crescente.get() : null;
		
		return this.usuarioService.getUsuarios(paramPagina, paramTamanho, paramOrdenarPor,paramCrescente);
	}
	
	@GetMapping("/{id}")
	@PermissaoRequerida(Permissao.USER)
	public UsuarioDTO obterUsuario(@PathVariable Long id) throws BusinessException {
		return this.usuarioService.getUsuario(id);
	}
	
	
}
