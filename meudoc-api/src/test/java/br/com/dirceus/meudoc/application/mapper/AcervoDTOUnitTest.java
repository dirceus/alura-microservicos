package br.com.dirceus.meudoc.application.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import br.com.dirceus.meudoc.application.dto.AcervoDTO;
import br.com.dirceus.meudoc.domain.model.acervo.Acervo;

class AcervoDTOUnitTest {

	private ModelMapper modelMapper = new ModelMapper();
	
	@Test
	void deveConverterAcervoEmAcervoDTOComAtributosNulos() {
		Acervo acervo = new Acervo();
		AcervoDTO dto = modelMapper.map(acervo, AcervoDTO.class);
		
		assertEquals(acervo.getId(), dto.getId());
		assertEquals(acervo.getNome(), dto.getNome());
	}
	
	@Test
	void deveConverterAcervoEmAcervoDTOComAtributosNaoNulos() {
		Acervo acervo = new Acervo();
		acervo.setId("Id do Acervo");
		acervo.setNome("Nome do Acervo");
		AcervoDTO dto = modelMapper.map(acervo, AcervoDTO.class);
		
		assertEquals(acervo.getId(), dto.getId());
		assertEquals(acervo.getNome(), dto.getNome());
	}

	@Test
	void deveConverterAcervoDTOEmAcervoComAtributosNulos() {
	
		AcervoDTO dto = new AcervoDTO();
		Acervo acervo = modelMapper.map(dto, Acervo.class);
		
		assertEquals(dto.getId(), acervo.getId());
		assertEquals(dto.getNome(), acervo.getNome());
	}
	
	@Test
	void deveConverterAcervoDTOEmAcervoComAtributosNaoNulos() {
		AcervoDTO dto = new AcervoDTO();
		dto.setId("Id do AcervoDTO");
		dto.setNome("Nome do AcervoDTO");
		Acervo acervo = modelMapper.map(dto, Acervo.class);
		
		assertEquals(dto.getId(), acervo.getId());
		assertEquals(dto.getNome(), acervo.getNome());
	}
	
	
}
