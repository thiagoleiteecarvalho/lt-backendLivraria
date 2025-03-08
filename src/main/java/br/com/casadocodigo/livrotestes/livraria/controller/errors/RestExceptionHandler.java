package br.com.casadocodigo.livrotestes.livraria.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoInvalidoException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoNaoPreenchidoException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.EstoqueLivroException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.IntegracaoOpenLibraryException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.LivroExistenteException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.LivroInexistenteException;
import br.com.casadocodigo.livrotestes.livraria.application.excecoes.ParametrosPesquisaLivroException;
import br.com.casadocodigo.livrotestes.livraria.domain.cover.ErrorCover;

/**
 * Controller responável por capturar erros de négocio e fornecer uma resposta amigal parao cliente da API.
 * 
 * @author Thiago Leite e Fred Viana
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
     * Trata erros de negócio relativos a campos não preenchidos na pesuisa de livros.
     * @param erro Exceção des campos inválidos 
     * @return Entidade representando um erro para ser retornado ao cliente da API. 
     */
    @ExceptionHandler(ParametrosPesquisaLivroException.class)
    public ResponseEntity<ErrorCover> trataPesquisaLivros(ParametrosPesquisaLivroException erro) {
    	
    	ErrorCover erroCover = new ErrorCover();
    	erroCover.setMensagem(erro.getMessage());
    	return new ResponseEntity<ErrorCover>(erroCover, HttpStatus.BAD_REQUEST);
    }
	
    /**
     * Trata erros de negócio relativos a campos não preenchidos.
     * @param erro Exceção de campo não preenchido
     * @return Entidade representando um erro para ser retornado ao cliente da API. 
     */
    @ExceptionHandler(CampoNaoPreenchidoException.class)
    public ResponseEntity<ErrorCover> trataCampoNaoPreenchido(CampoNaoPreenchidoException erro) {
    	
    	ErrorCover erroCover = new ErrorCover();
    	erroCover.setMensagem(erro.getMessage());
    	return new ResponseEntity<ErrorCover>(erroCover, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Trata erros de negócio relativos a campos invalidos.
     * @param erro Exceção de campo inválido
     * @return Entidade representando um erro para ser retornado ao cliente da API. 
     */
    @ExceptionHandler(CampoInvalidoException.class)
    public ResponseEntity<ErrorCover> trataCampoInvalido(CampoInvalidoException erro) {
    	
    	ErrorCover erroCover = new ErrorCover();
    	erroCover.setMensagem(erro.getMessage());
    	return new ResponseEntity<ErrorCover>(erroCover, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Trata erros de negócio relativos livro inexistente.
     * @param erro Exceção de campo inválido
     * @return Entidade representando um erro para ser retornado ao cliente da API. 
     */
    @ExceptionHandler(LivroInexistenteException.class)
    public ResponseEntity<ErrorCover> trataLivroInexistente(LivroInexistenteException erro) {
    	
    	ErrorCover erroCover = new ErrorCover();
    	erroCover.setMensagem(erro.getMessage());
    	return new ResponseEntity<ErrorCover>(erroCover, HttpStatus.NOT_FOUND);
    }

    /**
     * Trata erros de negócio relativos livro existente.
     * @param erro Exceção de campo inválido
     * @return Entidade representando um erro para ser retornado ao cliente da API. 
     */
    @ExceptionHandler(LivroExistenteException.class)
    public ResponseEntity<ErrorCover> trataLivroExistente(LivroExistenteException erro) {
    	
    	ErrorCover erroCover = new ErrorCover();
    	erroCover.setMensagem(erro.getMessage());
    	return new ResponseEntity<ErrorCover>(erroCover, HttpStatus.CONFLICT);
    }
    
    /**
     * Trata erros de negócio relativos ao estoque de livros.
     * @param erro Exceção de campo inválido
     * @return Entidade representando um erro para ser retornado ao cliente da API. 
     */
    @ExceptionHandler(EstoqueLivroException.class)
    public ResponseEntity<ErrorCover> trataEstoqueLivro(EstoqueLivroException erro) {
    	
    	ErrorCover erroCover = new ErrorCover();
    	erroCover.setMensagem(erro.getMessage());
    	return new ResponseEntity<ErrorCover>(erroCover, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Trata erros de negócio relativos a integraçãop com a OpenLibrary.
     * @param erro Exceção de integração de API
     * @return Entidade representando um erro para ser retornado ao cliente da API. 
     */
    @ExceptionHandler(IntegracaoOpenLibraryException.class)
    public ResponseEntity<ErrorCover> trataIntegracaoOpenLibrary(IntegracaoOpenLibraryException erro) {
    	
    	ErrorCover erroCover = new ErrorCover();
    	erroCover.setMensagem(erro.getMessage());
    	return new ResponseEntity<ErrorCover>(erroCover, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}