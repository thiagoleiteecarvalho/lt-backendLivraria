package br.com.casadocodigo.livrotestes.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoInvalidoException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoNaoPreenchidoException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.EstoqueLivroException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.IntegracaoOpenLibraryException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.LivroExistenteException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.LivroInexistenteException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.ParametrosPesquisaLivroException;
import br.com.casadocodigo.livrotestes.livraria.application.service.LivroService;
import br.com.casadocodigo.livrotestes.livraria.domain.cover.CategoriaCover;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Livro;

/**
 * Controller que disponibiliza a API do backend de livros.
 * @author Thiago Leite e Fred Viana
 */
@RestController
public class LivroController {

	/**
	 * {@link LivroService}.
	 */
	@Autowired
	private LivroService livroService;
	
	/**
	 * Retorna uma lista de categorias de livros. Ver {@link CategoriaCover}.
	 * @return Lista de categorias.
	 */
	@GetMapping(path = "/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CategoriaCover>> listarCategorias() {
		return ResponseEntity.ok(livroService.listarCategorias());
	}
	
	/**
	 * Retorna um livro em específico
	 * @param id Livro a ser consultado
	 * @return Livro encontrado
	 */
	@GetMapping(path = "/livro/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Livro> consultar(@PathVariable long id) throws LivroInexistenteException {
		return ResponseEntity.ok(livroService.consultar(id));
	}
	
	/**
	 * Pesquisa um livro a partir dos parâmetros informados
	 * @param id Livro a ser consultado
	 * @return Livro encontrado
	 * @throws ParametrosPesquisaLivroException Erro ao pesquisar livros
	 */
	@GetMapping(path = "/livros", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Livro>> pesquisar(@RequestParam String titulo, @RequestParam String isbn, @RequestParam String editora) throws LivroInexistenteException, ParametrosPesquisaLivroException {		
		return ResponseEntity.ok(livroService.pesquisar(titulo, isbn, editora));
	}
	
	/**
	 * Salva o novo livro.
	 * @param livro Livro a ser criado
	 * @return Novo livro
	 * @throws CampoNaoPreenchidoException Erro de campos ausentes.
	 * @throws CampoInvalidoException Erro de campos inválidos
	 * @throws LivroExistenteException Erro de livro existente 
	 * @throws IntegracaoOpenLibraryException Erro na integração com a OpenLibrary
	 */
	@PostMapping(path = "/livro", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Livro> salvar(@RequestBody Livro livro) throws CampoNaoPreenchidoException, CampoInvalidoException, LivroExistenteException, IntegracaoOpenLibraryException {
		return ResponseEntity.ok(livroService.salvar(livro));
	}
	
	/**
	 * Exclui um livro específico.
	 * @param id Livro a ser excluido
	 * @throws EstoqueLivroException Erro de estoque
	 * @throws LivroInexistenteException Erro de livro inexistente
	 */
	@DeleteMapping(path = "/livro/{id}")
	public ResponseEntity<?> excluir(@PathVariable long id) throws EstoqueLivroException, LivroInexistenteException {
		
		livroService.excluir(id);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * Atualioza um livro.
	 * @param livro Livro a ser atualizado.
	 */
	@PutMapping(path = "/livro", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void atualizar(@RequestBody Livro livro) {
		livroService.atualizar(livro);
	}

}
