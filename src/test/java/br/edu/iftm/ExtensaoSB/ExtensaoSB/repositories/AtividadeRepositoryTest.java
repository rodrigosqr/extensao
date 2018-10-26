package br.edu.iftm.ExtensaoSB.ExtensaoSB.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.edu.iftm.ExtensaoSB.ExtensaoSB.domain.Atividade;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AtividadeRepositoryTest {

	@Autowired
	private AtividadeRepository repository;

	@Test
	public void verificaQuantidadeAtividadesCadastradas() {
		Page<Atividade> atividades = this.repository.findAll(PageRequest.of(0, 10));
		assertThat(atividades.getTotalElements()).isGreaterThan(1);
	}
	
	@Test
	public void findByNome() {
		List<Atividade> atividades = this.repository.findByNomeContainingIgnoreCase("GDG");
		assertThat(atividades.size()).isEqualTo(1);
	}
	
	@Test 
	public void find() {
		List<Atividade> atividades = this.repository.findByNomeContainingIgnoreCase("Carlos");
		assertThat(atividades.size()).isEqualTo(0);
	}
	
	@Test
	public void saveTest() {
		Atividade atividade = new Atividade(null, "Test save repository");
		Atividade atividadeSalvo = this.repository.save(atividade);
		assertThat(atividadeSalvo.getId()).isNotNull();
	}
	
	@Test
	public void updateTest() {
		Atividade atividade = new Atividade(3, "Test save repository2");
		Atividade atividadeSalvo = this.repository.save(atividade);
		assertThat(atividadeSalvo.getNome()).containsIgnoringCase("repository2");
	}
	
	@Test
	public void deleteTest() {
		Atividade atividade = new Atividade(3, "Test save repository2");
		this.repository.delete(atividade);
		
		Optional<Atividade> atividadeExcluida = this.repository.findById(atividade.getId());
		assertThat(atividadeExcluida).isNotPresent();
	}
}
