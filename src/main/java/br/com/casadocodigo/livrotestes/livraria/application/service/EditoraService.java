package br.com.casadocodigo.livrotestes.livraria.application.service;

import static br.com.casadocodigo.livrotestes.livraria.application.util.MessagensUtil.getMensagem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.casadocodigo.livrotestes.livraria.application.excecoes.CampoInvalidoException;
import br.com.casadocodigo.livrotestes.livraria.application.util.CnpjValidator;
import br.com.casadocodigo.livrotestes.livraria.domain.entity.Editora;
import br.com.casadocodigo.livrotestes.livraria.domain.repository.JpaEditoraRepository;

/**
 * Classe de negócio para manipular a entidade {@link Editora}.
 * @author Thiago Leite  e Fred Viana
 */
@Service
public class EditoraService {

	/**
	 * {@link JpaEditoraRepository}.
	 */
	@Autowired
	private JpaEditoraRepository editoraRepository;

	/**
	 * Lista todos as editoras cadastradas.
	 * 
	 * @return Lista de editoras existentes.
	 */
	public List<Editora> listar() {
		return editoraRepository.findAll();
	}
	
	/**
	 * Cadastro uma nova editora.
	 * 
	 * @param editora Editora a ser salva
	 * @return Nova editora cadastrada.
	 * @throws CampoInvalidoException Erro na validade de campos
	 */
	public Editora salvar(Editora editora) throws CampoInvalidoException {

		if (!CnpjValidator.isValido(editora.getCnpj())) {
			throw new CampoInvalidoException(getMensagem("editora.cnpj.invalido"));
		}

		return editoraRepository.save(editora);
	}

	/**
	 * Obtem o percentual de desconto que a Editora poode disponibilizar.
	 * @param editora Editora a se obter o percentual de desconta, caso este exista
	 * @return Percentual de desconto
	 */
	public double obterDesconto(Editora editora) {
		return editoraRepository.getReferenceById(editora.getId()).getDesconto();
	}
}
