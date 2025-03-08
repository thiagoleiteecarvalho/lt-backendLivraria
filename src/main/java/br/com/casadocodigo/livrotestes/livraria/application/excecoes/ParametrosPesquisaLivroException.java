package br.com.casadocodigo.livrotestes.livraria.application.excecoes;

/**
 * Exceção auxiliar para informar que os parâmetros de pesquisa de um ou mais livros está inconsistente.
 * 
 * @author Thiago Leite e Fred Viana
 */
public class ParametrosPesquisaLivroException extends Exception {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -4998006734080555015L;

	/**
	 * Construtor padrão.
	 * 
	 * @param mensagem Mensagem de erro configurada
	 */
	public ParametrosPesquisaLivroException(String mensagem) {
		super(mensagem);
	}

}
