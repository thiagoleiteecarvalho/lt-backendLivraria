package br.com.casadocodigo.livrotestes.livraria.application.service;

import static br.com.casadocodigo.livrotestes.livraria.application.util.MessagensUtil.getMensagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.livrotestes.livraria.application.delegate.IsbnDelegate;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoInvalidoException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoNaoPreenchidoException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.EstoqueLivroException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.IntegracaoOpenLibraryException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.LivroExistenteException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.LivroInexistenteException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.ParametrosPesquisaLivroException;
import br.com.casadocodigo.livrotestes.livraria.application.util.IsbnValidator;
import br.com.casadocodigo.livrotestes.livraria.application.util.LivroValidator;
import br.com.casadocodigo.livrotestes.livraria.application.util.StringUtils;
import br.com.casadocodigo.livrotestes.livraria.domain.cover.CategoriaCover;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Categoria;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Livro;
import br.com.casadocodigo.livrotestes.livraria.domain.repository.JpaLivroRepository;
import jakarta.persistence.EntityNotFoundException;

/**
 * Classe de negócio para manipular a entidade {@link Livro}.
 * 
 * @author Thiago Leite e Fred Viana
 */
@Service
public class LivroService {

	/**
	 * {@link JpaLivroRepository}.
	 */
	@Autowired
	private JpaLivroRepository livroRepository;

	/**
	 * {@link IsbnDelegate}.
	 */
	@Autowired
	private IsbnDelegate isbnDelegate;
	
	/**
	 * {@link EditoraService}.
	 */
	@Autowired
	private EditoraService editoraService;

	/**
	 * Lista todos os livros cadastrados.
	 * 
	 * @return Lista de livros existentes.
	 */
	public List<Livro> listar() {
		return livroRepository.findAll();
	}

	/**
	 * Consulta um livro específico para edição.
	 * 
	 * @param livro Livro a ser obtido
	 * @return Livro encontrado.
	 * @throws LivroInexistenteException Livro não encontrado
	 */
	public Livro consultar(long idLivro) throws LivroInexistenteException {
		
		try {
			return this.livroRepository.getReferenceById(idLivro);
		} catch (EntityNotFoundException e) {
			throw new LivroInexistenteException(getMensagem("livro.isbn.invalido"));
		}
	}

	/**
	 * Cadastro um novo livro.
	 * 
	 * @param livro Livro a ser salvo
	 * @return Novo livro cadastrado.
	 * @throws CampoNaoPreenchidoException Erro na validação de campos obrigatório
	 * @throws CampoInvalidoException Erro na validade de campos
	 * @throws LivroExistenteException Erro de livro já existente
	 * @throws IntegracaoOpenLibraryException Erro na integração com a OpneLibrary
	 */
	@Transactional
	public Livro salvar(Livro livro) throws CampoNaoPreenchidoException, CampoInvalidoException, LivroExistenteException, IntegracaoOpenLibraryException {

		if (!LivroValidator.isbnPreenchido(livro.getIsbn())) {
			throw new CampoNaoPreenchidoException(getMensagem("livro.isbn.obrigatorio"));
		} else if (!IsbnValidator.isValido(livro.getIsbn())) {
			throw new CampoInvalidoException(getMensagem("livro.isbn.invalido"));
		}

		boolean isbnReal = isbnDelegate.isbnReal(livro.getIsbn());
		if (!isbnReal) {
			throw new CampoInvalidoException(getMensagem("livro.isbn.falso"));
		}

		if (!LivroValidator.tituloPreenchido(livro.getTitulo())) {
			throw new CampoNaoPreenchidoException(getMensagem("livro.titulo.obrigatorio"));
		}

		if (!LivroValidator.categoriaPreenchida(livro.getCategoria())) {
			throw new CampoNaoPreenchidoException(getMensagem("livro.categoria.obrigatorio"));
		}

		if (!LivroValidator.autorPreenchido(livro.getAutor())) {
			throw new CampoNaoPreenchidoException(getMensagem("livro.autor.obrigatorio"));
		}

		Optional<Livro> resultado = livroRepository.obterPorIsbn(livro.getIsbn());
		
		double percentualDesconto = this.editoraService.obterDesconto(livro.getEditora());
		
		if (percentualDesconto != 0.0) {
			livro.setPrecoDesconto(livro.getPreco() * percentualDesconto);
		}
		
		if (resultado.isEmpty()) {
			return livroRepository.save(livro);
		} else {
			throw new LivroExistenteException(getMensagem("livro.isbn.existente"));
		}
		
	}

	/**
	 * Atualiza o livro com os novos dados.
	 * @param livro Livro a ser atualizado
	 * @return Livro com os novos valores.
	 */
	@Transactional
	public Livro atualizar(Livro livro) {
		
		double percentualDesconto = this.editoraService.obterDesconto(livro.getEditora());
		
		if (percentualDesconto != 0.0) {
			livro.setPrecoDesconto(livro.getPreco() * percentualDesconto);
		}
		
		return this.livroRepository.save(livro);
	}
	
	/**
	 * Excluie um livro, caso sua quantidade seja 0(zero). Caso contrário uma
	 * mensagem indicando a falha na exclusão é fornecida.
	 * 
	 * @param id Id dolivro a ser excluido
	 * @throws EstoqueLivroException Erro durante a exclusao
	 * @throws LivroInexistenteException Livro não encontrado
	 */
	@Transactional
	public void excluir(long idLivro) throws EstoqueLivroException, LivroInexistenteException {
		
		Livro livroExclusao = consultar(idLivro);
		
		if (livroExclusao.getQuantidade() == 0) {
			livroRepository.deleteById(livroExclusao.getId());
		} else {
			throw new EstoqueLivroException(getMensagem("livro.estoque.erro"));
		}
	}

	/**
	 * Pesquisa um ou mais livros que se enquadrem nos parâmetros de pesquisa.
	 * @param titulo Título desejado
	 * @param isbn Isbn específico
	 * @param idEditora Editora desejada
	 * @return
	 * @throws ParametrosPesquisaLivroException Erro ao pesquisar livros
	 */
	public List<Livro> pesquisar(String titulo, String isbn, String idEditora) throws ParametrosPesquisaLivroException {

		long editora = Long.parseLong(idEditora);
		
		if (StringUtils.vazia(isbn) && StringUtils.vazia(titulo) && editora == 0) {
			return livroRepository.findAll();
		}
		
		if (StringUtils.naoVazia(isbn) && (StringUtils.naoVazia(titulo) || editora != 0)) {
			throw new ParametrosPesquisaLivroException(getMensagem("livro.pesqusia.isbn.erro"));
		}		
		
		if (StringUtils.naoVazia(titulo) || editora != 0) {
			return livroRepository.pesquisarPorTituloEditora(titulo, editora);
		}
		
		if (!IsbnValidator.isValido(isbn)) {
			throw new ParametrosPesquisaLivroException(getMensagem("livro.pesqusia.isbn.invalido"));
		} else {
			return livroRepository.obterPorIsbn(isbn).stream().collect(Collectors.toList());
		}
	}

	/** 
	 * Lista as categoriaqs de livros existentes.
	 * @return Lista de {@link CategoriaCover}. 
	 */
	public List<CategoriaCover> listarCategorias() {
		
		List<CategoriaCover> categorias = new ArrayList<>();
		
		for (Categoria categoria : Categoria.values()) {
			categorias.add(new CategoriaCover(categoria.getDescricao(), categoria.name()));
		}
		
		return categorias;
	}

}
