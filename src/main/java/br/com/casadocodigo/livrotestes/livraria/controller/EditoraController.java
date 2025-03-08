package br.com.casadocodigo.livrotestes.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoInvalidoException;
import br.com.casadocodigo.livrotestes.livraria.application.service.EditoraService;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Editora;

/**
 * Controller que disponibiliza a API do backend de produtos.
 * @author Thiago Leite/Fred Viana
 */
@RestController
public class EditoraController {

	/**
	 * {@link EditoraService}.
	 */
	@Autowired
	private EditoraService editoraService;
	
	@GetMapping(value = "/editoras", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Editora>> listar() {
		return ResponseEntity.ok(editoraService.listar());
	}
	
	@PostMapping(value = "/editora", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void salvar(@RequestBody Editora editora) throws CampoInvalidoException {
		editoraService.salvar(editora);
	}	
	
}
