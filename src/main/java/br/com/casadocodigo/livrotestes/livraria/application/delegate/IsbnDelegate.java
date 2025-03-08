package br.com.casadocodigo.livrotestes.livraria.application.delegate;

import static br.com.casadocodigo.livrotestes.livraria.application.util.MessagensUtil.getMensagem;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.casadocodigo.livrotestes.livraria.application.excecoes.IntegracaoOpenLibraryException;
import br.com.casadocodigo.livrotestes.livraria.domain.cover.BookCover;

/**
 * Delegate para abstratir a comunicação com o serviço de verificação de existencia de ISBN da OpenLibrary.
 * Padrão de URL: https://openlibrary.org/isbn/{numero}. 
 * Para entender a API acessar "https://openlibrary.org/dev/docs/api/books"
 * https://brasilapi.com.br/docs
 * 
 * @author Thiago Leite e Fred Viana
 */
@Component
public class IsbnDelegate {

	/**
	 * URL para a API de ISBN do OpenLibrary.
	 */
	private static final String URL = "https://openlibrary.org/isbn/{NUMBER}.json";
	
	/**
	 * Tentar obter um livro na API de ISBN do OpenLibrary para assim verificar a veracidade do número.
	 * @param isbn Número de ISBN a ser verificado
	 * @return true caso seja um número real. false caso contrário.
	 * @throws IntegracaoOpenLibraryException Erro na integração com a OpenLibrary
	 */
	public boolean isbnReal(String isbn) throws IntegracaoOpenLibraryException {
		
		try {
			
			RestTemplate restTemplate = new RestTemplate();
			
			BookCover book = restTemplate.getForObject(URL.replace("{NUMBER}", isbn), BookCover.class);
			
			return book != null; 
		} catch (RestClientException e) {
			throw new IntegracaoOpenLibraryException(getMensagem("livro.isbn.integracao.erro"), e);
		}
	}
	
	/**
	 * Tentar obter um livro na API de ISBN do OpenLibrary.
	 * @param isbn Número de ISBN do livro a ser obtido
	 * @return true caso seja um número real. false caso contrário.
	 * @throws IntegracaoOpenLibraryException Erro na integração com a OpenLibrary
	 */
	public Optional<BookCover> obterLivro(String isbn) throws IntegracaoOpenLibraryException {
		
		try {
			
			RestTemplate restTemplate = new RestTemplate();
			
			BookCover book = restTemplate.getForObject(URL.replace("{NUMBER}", isbn), BookCover.class);
			
			return Optional.ofNullable(book); 
		} catch (RestClientException e) {
			throw new IntegracaoOpenLibraryException(getMensagem("livro.isbn.integracao.erro"), e);
		}
	}	
}
