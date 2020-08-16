package br.com.dirceus.meudoc.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dirceus.meudoc.application.dto.AcervoDTO;
import br.com.dirceus.meudoc.application.service.AcervoService;
import br.com.dirceus.meudoc.commons.exception.BusinessException;
import br.com.dirceus.meudoc.commons.security.Permissao;
import br.com.dirceus.meudoc.commons.security.PermissaoRequerida;


@RestController
@CrossOrigin
@RequestMapping("/acervo")
public class AcervoController {

	@Autowired
	private AcervoService acervoService;
	
	@PostMapping("/salvar")
	@PermissaoRequerida(Permissao.USER)
	public AcervoDTO salvar(@RequestBody AcervoDTO acervoDto) throws BusinessException {
		return acervoService.criar(acervoDto);
	}
	
}
