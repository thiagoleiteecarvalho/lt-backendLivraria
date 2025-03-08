package br.com.casadocodigo.livrotestes.livraria.application.util;

/**
 * Classe Helper para manipulação de Strings.
 * 
 * @author Thiago Leite e Fred Viana
 */
public final class StringUtils {

	private StringUtils() {
		throw new AssertionError("StringUtils é uma HelperClass e não pdoe ser instânciada");
	}

	/**
	 * String vazia.
	 */
	public static final String EMPTY_STRING = "";
	
	/**
	 * Verifica se uma tring está vazia.
	 * @param valor String a ser verificada
	 * @return true caso seja. falsecaso contrário
	 */
	public static final boolean vazia(String valor) {
		
		if (valor == null || valor.length() == 0) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Verifica se uma tring está vazia.
	 * @param valor String a ser verificada
	 * @return true caso seja. falsecaso contrário
	 */
	public static final boolean naoVazia(String valor) {
		return !vazia(valor);
	}	
	
}
