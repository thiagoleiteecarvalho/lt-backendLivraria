package br.com.casadocodigo.livrotestes.livraria.application.excecoes;

/**
 * Exceção auxiliar para informar que ocorreu um erro na integração com a API do
 * OpenLibrary.
 * 
 * @author Thiago Leite e Fred Viana
 */
public class IntegracaoOpenLibraryException extends Exception {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -2503738771646976109L;

	/**
	 * Construtor padrão.
	 * 
	 * @param mensagem Mensagem de erro configurada
	 * @param causa Causa riz do erro
	 */
	public IntegracaoOpenLibraryException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}