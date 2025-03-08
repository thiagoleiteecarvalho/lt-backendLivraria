package br.com.casadocodigo.livrotestes.livraria.application.excecoes;

/**
 * Exceção auxiliar para informara ausencia de campos obrigatórios.
 * 
 * @author Thiago Leite e Fred Viana
 */
public class CampoNaoPreenchidoException extends Exception {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -6977886512777714934L;

	/**
	 * COnstrutor padrão.
	 * @param mensagem Mensagem de erro configurada
	 */
	public CampoNaoPreenchidoException(String mensagem) {
		super(mensagem);
	}
}
