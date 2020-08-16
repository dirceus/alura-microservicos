package br.com.dirceus.meudoc.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.dirceus.meudoc.domain.model.acervo.Acervo;

public interface AcervoRepository extends MongoRepository<Acervo, Long>{

	public Acervo findByNome(String nome);

}
