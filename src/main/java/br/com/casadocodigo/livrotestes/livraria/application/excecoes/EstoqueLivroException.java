package br.com.casadocodigo.livrotestes.livraria.application.excecoes;

/**
 * Exceção auxiliar para informar que a exclusão de livro não pode ser realizada,
 * pois existem exemplos no estoque, ou seja sua quantidade não é 0(zero).
 * 
 * @author Thiago Leite e Fred Viana
 */
public class EstoqueLivroException extends Exception {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 6656182638412401161L;

	/**
	 * Construtor padrão.
	 * 
	 * @param mensagem Mensagem de erro configurada
	 */
	public EstoqueLivroException(String mensagem) {
		super(mensagem);
	}
}
