package br.com.casadocodigo.livrotestes.livraria.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Classe de mapeamento da entidade Livro.
 * @author Thiago Leite e Fred Viana
 */
@Entity
@Table(name = "tb_livro")
public class Livro implements Serializable {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = -3655095725090748947L;

	/**
	 * Id. Sequence para a entidade livro.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * ISBN(International Standard Book Number) do livro.
	 */
	@Column(name = "nr_isbn")
	private String isbn;

	/**
	 * Título principal.
	 */
	@Column(name = "tx_titulo")
	private String titulo;
	
	/**
	 * Título secundário.
	 */
	@Column(name = "tx_sub_titulo")
	private String subTitulo;
	
	/**
	 * Categoria do livro. Ver {@link Categoria}.
	 */
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	/**
	 * Nome do autor do livro.
	 */
	@Column(name = "tx_nome_autor")
	private String autor;
	
	/**
	 * Número da edição do livro.
	 */
	@Column(name = "nr_edicao")
	private String edicao;
	
	/**
	 * Data de publicação do livro.
	 */
	@Column(name = "dt_publicacao")
	private LocalDateTime dataPublicacao; 

	/**
	 * Editora a qual o livro pertence. Ver {@link Editora}.
	 */
	@ManyToOne
	@JoinColumn(name = "id_editora")
	private Editora editora;
	
	/**
	 * Texto descritivo do livro.
	 */
	@Column(name = "cl_sinopse")
	private String sinopse;
	
	/**
	 * Quantidade de páginas que o livro possui.
	 */
	@Column(name = "nr_quantidade_paginas")
	private int quantidadePaginas;
	
	/**
	 * Quantidade de livros existentes. 
	 */
	@Column(name = "nr_quantidade")
	private int quantidade;
	
	/**
	 * Preço do livro. 
	 */
	@Column(name = "nr_preco")
	private double preco;
	 
	/**
	 * Preço do livro. 
	 */
	@Column(name = "nr_preco_desconto")
	private double precoDesconto;

	/**
	 * Imagem da capa do livro.
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "bl_capa")
	private String capa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	public void setSubTitulo(String subTitulo) {
		this.subTitulo = subTitulo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public LocalDateTime getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(LocalDateTime dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public int getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public void setQuantidadePaginas(int quantidadePaginas) {
		this.quantidadePaginas = quantidadePaginas;
	}
	
	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public double getPrecoDesconto() {
		return precoDesconto;
	}
	
	public void setPrecoDesconto(double precoDesconto) {
		this.precoDesconto = precoDesconto;
	}
	
	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Livro livro) {
			return Objects.equals(isbn, livro.isbn);
		}
		
		return false;
	}
	
}
