package br.com.casadocodigo.livrotestes.livraria.application.service;


import static br.com.casadocodigo.livrotestes.livraria.application.util.MessagensUtil.getMensagem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.casadocodigo.livrotestes.livraria.application.excecoes.EstoqueLivroException;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Editora;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Livro;
import br.com.casadocodigo.livrotestes.livraria.domain.repository.JpaLivroRepository;

/**
 * Classe de teste unitário para a classe de service {@link LivroService}.
 * @author Thiago Leite e Fred Viana
 */
@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

	/**
	 * Mock para {@link EditoraService}.
	 */
	@Mock
	private EditoraService editoraService;
	
	/**
	 * Mock para {@link JpaLivroRepository}.
	 */
	@Mock
	private JpaLivroRepository livroRepository;
	
	/**
	 * Mock para {@link LivroService}.
	 */
	@InjectMocks
	private LivroService livroService;	
	
	/**
	 * Testa o método {@link LivroService#atualizar(Livro)} quando a editora do livro tem desconto configurado.
	 */
	@Test
	public void testAtualizarLivroComDesconto() {
		
		//Definição de parâmetros de entrada.
		Editora editora = new Editora();
		editora.setId(1L);
		editora.setDesconto(0.1D);
		
		Livro livro = new Livro();
		livro.setIsbn("9785934588347");
		livro.setPreco(79.9D);
		livro.setEditora(editora);
				
		//Configuração do Mock para EditoraService 
		when(this.editoraService.obterDesconto(editora)).thenReturn(0.1D);
		
		//Configuração do Mock para JpaLivroRepository
		when(this.livroRepository.save(livro)).thenReturn(livro);
		
		//Execução do método a ser testado
		Livro resultado = this.livroService.atualizar(livro);
		
		//Verificação de execução de mocks configurados
		verify(this.editoraService).obterDesconto(editora);
		verify(this.livroRepository).save(livro);
		
		double valorComDesconto = livro.getPreco() * editora.getDesconto();
		
		//Verificação de resultado do método
		assertEquals(valorComDesconto, resultado.getPrecoDesconto());
	}
	
	/**
	 * Testa o método {@link LivroService#excluir(Long)} quando um erro ocorre devido a quantidade não ser 0.
	 */
	@Test
	public void testExcluirComErro() {
	
		//Definição de parâmetros de entrada.
		Livro livro = new Livro();
		livro.setId(1L);
		livro.setIsbn("9785934588347");
		livro.setQuantidade(10);
		
		//Configuração do Mock para JpaLivroRepository
		when(this.livroRepository.getReferenceById(livro.getId())).thenReturn(livro);
		
		//Configuração do lançamento do erro de estoque e execução deo método que o lança
		EstoqueLivroException erro = assertThrows(EstoqueLivroException.class,
				() -> this.livroService.excluir(livro.getId()));
	
		//Verificação de execução de mocks configurados
		verify(this.livroRepository).getReferenceById(livro.getId());
		
		assertEquals(erro.getMessage(), getMensagem("livro.estoque.erro"));
	}	
}
