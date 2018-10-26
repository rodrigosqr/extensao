package br.edu.iftm.ExtensaoSB.ExtensaoSB.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.iftm.ExtensaoSB.ExtensaoSB.domain.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Integer> {

	public List<Atividade> findByNomeContainingIgnoreCase(String nome);
}
