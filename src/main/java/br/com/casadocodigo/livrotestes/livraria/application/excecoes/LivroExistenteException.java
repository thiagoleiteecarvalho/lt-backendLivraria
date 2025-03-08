package br.com.casadocodigo.livrotestes.livraria.application.excecoes;

/**
 * Exceção auxiliar para informar que já existe um livro com  ISBN informado.
 * 
 * @author Thiago Leite e Fred Viana
 */
public class LivroExistenteException extends Exception {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 5858424183829347496L;

	/**
	 * Construtor padrão.
	 * 
	 * @param mensagem Mensagem de erro configurada
	 */
	public LivroExistenteException(String mensagem) {
		super(mensagem);
	}

}
