package br.com.casadocodigo.livrotestes.livraria.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.livrotestes.livraria.domain.entity.Editora;

/**
 * Classe de acesso aos dados para manipular a entidade {@link Editora}.
 * @author Thiago Leite e Fred Viana
 */
@Repository
public interface JpaEditoraRepository extends JpaRepository<Editora, Long> {

}
