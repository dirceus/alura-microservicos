package br.com.dirceus.meudoc.application.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dirceus.meudoc.application.dto.AcervoDTO;
import br.com.dirceus.meudoc.application.service.AcervoService;
import br.com.dirceus.meudoc.commons.exception.BusinessException;
import br.com.dirceus.meudoc.domain.model.acervo.Acervo;
import br.com.dirceus.meudoc.domain.repository.AcervoRepository;

@Service
public class AcervoServiceImpl implements AcervoService{

	@Autowired
	private AcervoRepository acervoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public AcervoDTO criar(AcervoDTO acervoDTO) throws BusinessException {
		Acervo acervo = acervoRepository.findByNome(acervoDTO.getNome());
		if(acervo != null) {
			throw new BusinessException("Já existe um acervo com o nome informado");
		}
		return salvar(acervoDTO);
	}
	
	@Override
	public AcervoDTO salvar(AcervoDTO acervoDTO) {
		
		Acervo acervo = modelMapper.map(acervoDTO, Acervo.class);
		acervo =  acervoRepository.save(acervo);
		return modelMapper.map(acervo, AcervoDTO.class);
		
	}

	

}
