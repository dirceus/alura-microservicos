package br.com.dirceus.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.dirceus.auth.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Usuario findByEmail(String email);
		
}
