package br.edu.iftm.ExtensaoSB.ExtensaoSB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.iftm.ExtensaoSB.ExtensaoSB.domain.Atividade;
import br.edu.iftm.ExtensaoSB.ExtensaoSB.repositories.AtividadeRepository;

@SpringBootApplication
public class ExtensaoSbApplication implements CommandLineRunner {
	
	@Autowired
	private AtividadeRepository atividadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ExtensaoSbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Atividade atividade = new Atividade();
		atividade.setNome("Simpos");
		atividadeRepository.save(atividade);
		
	}
}
