package br.com.casadocodigo.livrotestes.livraria.application.util;

/**
 * Classe Helper para validação de número de ISBN.
 * 
 * @author Thiago Leite e Fred Viana
 */
public final class IsbnValidator {

	private IsbnValidator() {
		throw new AssertionError("IsbnValidator é uma HelperClass e não pdoe ser instânciada");
	}

	/**
	 * Verifica se um número de ISBN é valido.
	 * 
	 * @param isbn Número de ISBN a ser validado.
	 * @return true caso seja válido. false caso contrário
	 */
	public static boolean isValido(String isbn) {

		if (isbn.length() != 13) {
			return false;
		}

		int soma = 0;
		for (int i = 0; i < 12; i++) {
			int digito = Character.getNumericValue(isbn.charAt(i));

			if (i % 2 == 0) {
				soma += digito;
			} else {
				soma += digito * 3;
			}
		}

		int verificador = 10 - (soma % 10);
		if (verificador == 10) {
			verificador = 0;
		}

		int ultimotDigito = Character.getNumericValue(isbn.charAt(12));

		return ultimotDigito == verificador;
	}

}
