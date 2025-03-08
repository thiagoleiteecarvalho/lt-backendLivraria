package br.com.casadocodigo.livrotestes.livraria.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.livrotestes.livraria.domain.entity.Livro;

/**
 * Classe de acesso aos dados para manipular a entidade {@link Livro}.
 * @author Thiago Leite/Fred Viana
 */
@Repository
public interface JpaLivroRepository extends JpaRepository<Livro, Long> {

	/**
	 * Retorno um livro a partir deseu ISBN.
	 * @param isbn ISBN de um livro específico
	 * @return O livro que possui o isbn ou nada, caso não exista
	 */
	@Query("select l from Livro l where l.isbn = :isbn")
	Optional<Livro> obterPorIsbn(@Param("isbn") String isbn);

	/**
	 * Retorno um livro a partir de um título e editora.
	 * @param titulo Título do livro
	 * @param idEditora Id de uma determinada editora
	 * @return Os livros que se encaixam nos parâmetros fornecidos ou uma lista vazia, caso contrário
	 */
	@Query("select l from Livro l where LOWER(l.titulo) like LOWER(CONCAT('%',:titulo,'%')) or l.editora.id = :idEditora")
	List<Livro> pesquisarPorTituloEditora(@Param("titulo") String titulo, @Param("idEditora") long idEditora);

}
