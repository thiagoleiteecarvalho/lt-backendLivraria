package br.com.casadocodigo.livrotestes.livraria.domain.cover;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe cover para encapsular o resultado da API do OpenLibrary.
 * @author Thiago Leite e Fred Viana
 */
public class BookCover {

	/**
	 * Titulo retonado pela API.
	 */
	@JsonProperty("title")
	private String titulo;
	
	/**
	 * ISBN retonado pela API.
	 */
	@JsonProperty("isbn_13")
	private String[] isbn;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String[] getIsbn() {
		return isbn;
	}

	public void setIsbn(String[] isbn) {
		this.isbn = isbn;
	}
	
}
