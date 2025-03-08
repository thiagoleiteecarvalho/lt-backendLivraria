package br.com.casadocodigo.livrotestes.livraria.domain.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Classe de mapeamento da entidade Editora.
 * @author Thiago Leite/Fred Viana
 */
@Entity
@Table(name = "tb_editora")
public class Editora implements Serializable { 

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 7961933115940957555L;

	/**
	 * Id. Sequence para a entidade livro.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Nome da editora do livro.
	 */
	@Column(name = "tx_nome")
	private String nome;

	/**
	 * CNPJ(Cadastro Nacional de Pessoa Jurídica) da editora.
	 */
	@Column(name = "tx_cnpj")
	private String cnpj;
	
	/**
	 * Desconto que a editora pode disponibilizar.
	 */
	@Column(name = "nr_desconto")
	private double desconto;	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	public double getDesconto() {
		return desconto;
	}
	
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	
	/**
	 * {@inheritDoc.}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cnpj);
	}
	
	/**
	 * {@inheritDoc.}
	 */
	@Override
	public boolean equals(Object obj) {
	
		if (obj instanceof Editora editora) {
			return Objects.equals(cnpj, editora.cnpj);
		}
	
		return false;
	}

	/**
	 * {@inheritDoc.}
	 */
	@Override
	public String toString() {
		return new StringBuilder("Editora[").append(nome).append("]").toString();
	}
}
