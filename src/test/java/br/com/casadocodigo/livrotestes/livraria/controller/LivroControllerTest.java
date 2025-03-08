package br.com.casadocodigo.livrotestes.livraria.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import br.com.casadocodigo.livrotestes.livraria.application.service.LivroService;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Livro;

/**
 * Classe de teste unitário para a classe de service {@link LivroController}.
 * @author Thiago Leite e Fred Viana
 */
@WebMvcTest(LivroController.class)
public class LivroControllerTest {

	/**
	 * {@link LivroService}.
	 */
	@MockitoBean
	private LivroService livroService;
	
	/**
	 * {@link MockMvc}.
	 */
	@Autowired
	private MockMvc mockMvc;
	
	/**
	 * Testa o end-point {@link LivroController#consultar(long)}.
	 * @throws Exception Erro na execução de teste
	 */
	@Test
	public void testConsultar() throws Exception {
		
		//Parâmetro
		Livro livro = new Livro();
		livro.setId(1L);
		livro.setTitulo("Orientação a Objetos");
		livro.setSubTitulo("Aprenda seus conceitos e suas aplicabilidades de forma efetiva");
		livro.setAutor("Thiago Leite");
	
		//Pré-execução
		when(livroService.consultar(livro.getId())).thenReturn(livro);
		
		//Integração
		mockMvc.perform(get("/livro/1"))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.titulo", is("Orientação a Objetos")))
			.andExpect(jsonPath("$").isNotEmpty());
	}
	
}
