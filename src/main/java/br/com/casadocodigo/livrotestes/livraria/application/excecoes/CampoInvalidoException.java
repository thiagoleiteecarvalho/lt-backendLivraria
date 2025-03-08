package br.com.casadocodigo.livrotestes.livraria.application.excecoes;

/**
 * Exceção auxiliar para informara invalidade de campos.
 * 
 * @author Thiago Leite e Fred Viana
 */
public class CampoInvalidoException extends Exception {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -2766853550275056537L;

	/**
	 * Construtor padrão.
	 * @param mensagem Mensagem de erro configurada
	 */
	public CampoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
