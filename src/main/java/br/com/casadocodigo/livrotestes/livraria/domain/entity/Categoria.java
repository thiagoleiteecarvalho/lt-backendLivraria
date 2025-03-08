package br.com.casadocodigo.livrotestes.livraria.domain.entity;

/**
 * Enum das categorias as quais os livro de TI podem pertencer.
 * @author Thiago Leite e Fred Viana
 */
public enum Categoria {

	PROGRAMACAO("Programação"),
	TESTES("Testes"),
	REQUISTITOS("Requisitos"),
	GESTAO("Gestão"),
	DADOS("Dados");
	
	private String descricao;
	
	/**
	 * Construtor.
	 * @param descricao Descrição da categoria
	 */
	Categoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
