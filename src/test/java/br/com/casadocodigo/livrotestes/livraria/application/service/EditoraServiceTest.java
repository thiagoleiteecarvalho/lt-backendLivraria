package br.com.casadocodigo.livrotestes.livraria.application.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoInvalidoException;
import br.com.casadocodigo.livrotestes.livraria.application.util.CnpjValidator;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Editora;
import br.com.casadocodigo.livrotestes.livraria.domain.repository.JpaEditoraRepository;
import br.com.casadocodigo.livrotestes.livraria.domain.repository.JpaLivroRepository;

/**
 * CClasse de teste unitário para a classe de service {@link EditoraService}.
 * @author Thiago Leite e Fred Viana
 */
@ExtendWith(MockitoExtension.class)
public class EditoraServiceTest {

	
	/**
	 * Mock para {@link JpaLivroRepository}.
	 */
	@Mock
	private JpaEditoraRepository editoraRepository;
	
	/**
	 * Mock para {@link EditoraService}.
	 */
	@InjectMocks
	private EditoraService editoraService;

	/**
	 * Testa o método {@link EditoraService#salvar(Editora)}.
	 * @throws CampoInvalidoException Erro de cnpj inválido
	 */
	//@Test
	public void testSalvarSucesso() throws CampoInvalidoException {

		//Parâmetros
		Editora editoraTransiente = new Editora();
		editoraTransiente.setNome("Casa do Código");
		editoraTransiente.setDesconto(0.15D);
		editoraTransiente.setCnpj("30.744.209/0001-20");
		
		Editora editoraPersistida = new Editora();
		editoraPersistida.setId(1L);
		editoraPersistida.setNome("Casa do Código");
		editoraPersistida.setDesconto(0.15D);
		editoraPersistida.setCnpj("30.744.209/0001-20");
		
		
		try (var mock = mockStatic(CnpjValidator.class)) {
			
			//Pré-execuções
			mock.when(() -> CnpjValidator.isValido(editoraTransiente.getCnpj())).thenReturn(true);
			
			when(editoraRepository.save(editoraTransiente)).thenReturn(editoraPersistida);
			
			//Teste
			var resultado = editoraService.salvar(editoraTransiente);
			
			//Pos-execuções
			mock.verify(() -> CnpjValidator.isValido(editoraTransiente.getCnpj()));
			verify((editoraRepository).save(editoraTransiente));
			
			//Validação
			assertEquals(resultado, editoraPersistida);			
		}
		
	}
	
}
