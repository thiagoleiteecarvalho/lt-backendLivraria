package br.com.casadocodigo.livrotestes.livraria.application.excecoes;

/**
 * Exceção auxiliar para informar que um livro não foi encontrado a partir de seu id.
 * 
 * @author Thiago Leite e Fred Viana
 */
public class LivroInexistenteException extends Exception {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 7447661364937197335L;

	/**
	 * Construtor padrão.
	 * 
	 * @param mensagem Mensagem de erro configurada
	 */
	public LivroInexistenteException(String mensagem) {
		super(mensagem);
	}
}
