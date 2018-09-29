package br.edu.iftm.ExtensaoSB.ExtensaoSB.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividades")
public class AtividadeResource {

	@GetMapping
	public String Listar() {
		return "Rest Ok";
	}

}
