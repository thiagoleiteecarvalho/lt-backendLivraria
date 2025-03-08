package br.com.casadocodigo.livrotestes.livraria.domain.cover;

/**
 * Classe cover para encapsular os erros de negócio e fornecer uma resposta amigável ao cliente da API.
 * @author Thiago Leite e Fred Viana
 */
public class ErrorCover {

	/**
	 * Mensagem de erro retornada, devido a alguma validação de negócio.
	 */
	private String mensagem;

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
}
