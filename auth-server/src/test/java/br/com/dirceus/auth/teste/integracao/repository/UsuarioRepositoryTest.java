package br.com.dirceus.auth.teste.integracao.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.dirceus.auth.model.Usuario;
import br.com.dirceus.auth.repository.UsuarioRepository;
import br.com.dirceus.meudoc.commons.security.Permissao;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UsuarioRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	public void quandoPesquisoPorEmailCadastradoEntaoReturnarUmUsuario() {
		List<Permissao> permissoes = new ArrayList<>();
		permissoes.add(Permissao.USER);
		Usuario usuario = new Usuario(null, "Dirceu", "dirceu.crb@gmail.com", "abc123", permissoes);
		entityManager.persist(usuario);
		entityManager.flush();
		
		Usuario encontrado = usuarioRepository.findByEmail("dirceu.crb@gmail.com");
		assertThat(encontrado.getNome()).isEqualTo(usuario.getNome());
	}
	
	
}
