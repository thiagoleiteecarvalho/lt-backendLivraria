package br.com.casadocodigo.livrotestes.livraria.application.util;

/**
 * Classe Helper para validação de número de CNPJ.
 * 
 * @author Thiago Leite e Fred Viana
 */
public final class CnpjValidator {

	private CnpjValidator() {
		throw new AssertionError("CnpjValidator é uma HelperClass e não pdoe ser instânciada");
	}

	/**
	 * Verifica se um número de CNPJ é valido.
	 * 
	 * @param cnpj Número de CNPJ a ser validado.
	 * @return true caso seja válido. false caso contrário
	 */
	public static boolean isValido(String cnpj) {

		// Remove caracteres não numéricos
		cnpj = cnpj.replaceAll("[^\\d]", "");

		if (cnpj.length() != 14) {
			return false;
		}

		// Calcula o primeiro dígito verificador
		int soma = 0;
		int fator = 5;
		for (int i = 0; i < 12; i++) {

			int digito = Character.getNumericValue(cnpj.charAt(i));
			soma += digito * fator;
			fator = (fator == 2) ? 9 : fator - 1;
		}
		int primeiroDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

		// Calcula o segundo dígito verificador
		soma = 0;
		fator = 6;
		for (int i = 0; i < 13; i++) {

			int digito = Character.getNumericValue(cnpj.charAt(i));
			soma += digito * fator;
			fator = (fator == 2) ? 9 : fator - 1;
		}
		int segundoDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);

		// Verifica se os dígitos verificadores calculados são iguais aos dígitos
		// verificadores do CNPJ informado
		int digito13 = Character.getNumericValue(cnpj.charAt(12));
		int digito14 = Character.getNumericValue(cnpj.charAt(13));

		return primeiroDigito == digito13 && segundoDigito == digito14;
	}

}
