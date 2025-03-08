package br.com.casadocodigo.livrotestes.livraria.application.util;

import br.com.casadocodigo.livrotestes.livraria.domain.entity.Categoria;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Livro;

/**
 * Classe Helper para validação de campos da entidade {@link Livro}. A validação
 * do campo ISBN é feito separadamente. Ver {@link IsbnValidator}.
 * 
 * @author Thiago Leite e Fred Viana
 */
public final class LivroValidator {

	private LivroValidator() {
		throw new AssertionError("LivroValidator é uma HelperClass e não pdoe ser instânciada");
	}

	/**
	 * Verifica se o isbn foi fornecido.
	 * 
	 * @param isbn Número de ISBN a ser verificado.
	 * @return true caso seja válido. false caso contrário
	 */
	public static boolean isbnPreenchido(String isbn) {
		return !StringUtils.vazia(isbn); 
	}

	/**
	 * Verifica se o titulo foi fornecido.
	 * 
	 * @param titulo Titulo a ser verificado
	 * @return true caso seja válido. false caso contrário
	 */
	public static boolean tituloPreenchido(String titulo) {
		return !StringUtils.vazia(titulo); 
	}

	/**
	 * Verifica se a categoria foi fornecida.
	 * 
	 * @param titulo Titulo a ser verificado
	 * @return true caso seja válido. false caso contrário
	 */
	public static boolean categoriaPreenchida(Categoria categoria) {
		return categoria != null; 
	}
	
	/**
	 * Verifica se o nome do autor foi fornecido.
	 * 
	 * @param nome Nome a ser verificado
	 * @return true caso seja válido. false caso contrário
	 */
	public static boolean autorPreenchido(String nome) {
		return !StringUtils.vazia(nome); 
	}	
	
}
