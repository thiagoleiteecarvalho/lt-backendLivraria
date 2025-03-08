package br.com.casadocodigo.livrotestes.livraria.domain.cover;

import br.com.casadocodigo.livrotestes.livraria.domain.entity.Categoria;

/**
 * Classe cover para encapsular a lista de categorias de livros. Ver {@link Catogoria}.
 * @author Thiago Leite e Fred Viana
 */
public class CategoriaCover {

	/**
	 * Nome da categoria. Ver {@link Categoria#getDescricao()}.
	 */
	private String nome;
	
	/**
	 * Valor da categoria. Ver atributos de {@link Categoria}.
	 */
	private String valor;

	/**
	 * Construtor.
	 * @param nome Nome da categoria.
	 * @param valor Valor da categoria
	 */
	public CategoriaCover(String nome, String valor) {
		
		this.nome = nome;
		this.valor = valor;
	}
	
	public String getNome() {
		return nome;
	}

	public String getValor() {
		return valor;
	}
	
}
