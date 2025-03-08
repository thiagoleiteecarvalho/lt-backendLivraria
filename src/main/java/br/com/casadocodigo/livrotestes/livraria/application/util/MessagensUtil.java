package br.com.casadocodigo.livrotestes.livraria.application.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Classe Helper para manipulação de mensagens para a aplicação.
 * 
 * @author Thiago Leite e Fred Viana
 */
public class MessagensUtil {
	
	private MessagensUtil() {
		throw new AssertionError("MessagensUtil é uma HelperClass e não pdoe ser instânciada");
	}
	
	/**
	 * Nome do arquivo de mensagens.
	 */
	private static final String BUNDLE_NAME = "mensagens"; 
	
	/**
	 * {@link ResourceBundle}.
	 */
	private static final ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * Retorna uma mensagem a partir de sua chave de configuração.
	 * @param chave Chave a se obetr a mensagem
	 * @return Mensagem encontrda ou um erro de indicando sua não configuração 
	 */
	public static String getMensagem(String chave) {
		try {
			return bundle.getString(chave);
		} catch (MissingResourceException e) {
			
			String mensagem = "Chave não configurada: %s";
			throw new IllegalArgumentException(String.format(mensagem, chave));
		}
	}
		
}