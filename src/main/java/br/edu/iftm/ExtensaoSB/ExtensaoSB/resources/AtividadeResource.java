package br.edu.iftm.ExtensaoSB.ExtensaoSB.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.ExtensaoSB.ExtensaoSB.domain.Atividade;
import br.edu.iftm.ExtensaoSB.ExtensaoSB.services.AtividadeService;

@RestController
@RequestMapping("/atividades")
public class AtividadeResource {

	@Autowired
	private AtividadeService atividadeService;

	@GetMapping(path = "{id}")
	public ResponseEntity<Atividade> buscar(@PathVariable("id") Integer id) {
		Optional<Atividade> atividade = atividadeService.buscar(id);
		return atividade.isPresent() ? ResponseEntity.ok(atividade.get())
				: new ResponseEntity<Atividade>(HttpStatus.NOT_FOUND);
	}
}
