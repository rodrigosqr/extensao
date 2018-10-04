package br.edu.iftm.ExtensaoSB.ExtensaoSB.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.ExtensaoSB.ExtensaoSB.domain.Atividade;
import br.edu.iftm.ExtensaoSB.ExtensaoSB.repositories.AtividadeRepository;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	public Optional<Atividade> buscar(Integer id) {
		return atividadeRepository.findById(id);
	}
}
