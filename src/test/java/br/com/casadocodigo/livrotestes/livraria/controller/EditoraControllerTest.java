package br.com.casadocodigo.livrotestes.livraria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoInvalidoException;
import br.com.casadocodigo.livrotestes.livraria.application.service.EditoraService;
import br.com.casadocodigo.livrotestes.livraria.domain.cover.ErrorCover;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Editora;

/**
 * Classe de teste de integrção para a classe de service {@link EditoraController}.
 * @author Thiago Leite e Fred Viana
 */
@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EditoraControllerTest {

	/**
	 * {@link EditoraService}.
	 */
	@Autowired
	private EditoraService editoraService;
	
	/**
	 * Classe de teste para o {@link RestTemplate}.
	 */
	@Autowired
	private TestRestTemplate restTemplateTest;
	
	/**
	 * Testa o end-point {@link EditoraController#salvar(Editora)}.
	 */
	@Test
	public void testSalvarSucesso() throws CampoInvalidoException {
		
		//Parâmetro
		Editora editoraTransiente = new Editora();
		editoraTransiente.setNome("Casa do Código");
		editoraTransiente.setDesconto(0.15D);
		editoraTransiente.setCnpj("30.744.209/0001-20");
		
		
		//Teste integração
		editoraService.salvar(editoraTransiente);
		List<Editora> editoras = restTemplateTest.exchange("/editoras",HttpMethod.GET, null, new ParameterizedTypeReference<List<Editora>>() {
		}).getBody();
		
		//Validações
		assertEquals(1, editoras.size());
		assertEquals(editoraTransiente.getCnpj(), editoras.get(0).getCnpj());
		assertNotNull(editoras.get(0).getId());
	}
	
	
	/**
	 * Testa o end-point {@link EditoraController#salvar(Editora)}.
	 */
	@Test
	public void tetSalvarErro() throws CampoInvalidoException {
		
		//Parâmetro
		Editora editoraTransiente = new Editora();
		editoraTransiente.setNome("Casa do Código");
		editoraTransiente.setDesconto(0.15D);
		editoraTransiente.setCnpj("33.744.209/0001-20");
		
		//Teste integração
		CampoInvalidoException campoInvalidoException = assertThrows(CampoInvalidoException.class,
				() -> editoraService.salvar(editoraTransiente));				
		
		ResponseEntity<ErrorCover> erro = restTemplateTest.postForEntity("/editora", editoraTransiente, ErrorCover.class);
		
		//Validações
		assertEquals(erro.getBody().getMensagem(), "CNPJ inválido");
		assertEquals(erro.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
		assertEquals(campoInvalidoException.getMessage(), erro.getBody().getMensagem());
	}
	
}
