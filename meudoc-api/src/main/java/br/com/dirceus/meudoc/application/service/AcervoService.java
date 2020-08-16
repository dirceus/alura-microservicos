package br.com.dirceus.meudoc.application.service;

import br.com.dirceus.meudoc.application.dto.AcervoDTO;
import br.com.dirceus.meudoc.commons.exception.BusinessException;

public interface AcervoService {

	public AcervoDTO criar(AcervoDTO acervoDTO) throws BusinessException;
	public AcervoDTO salvar(AcervoDTO acervoDTO);
	
}
