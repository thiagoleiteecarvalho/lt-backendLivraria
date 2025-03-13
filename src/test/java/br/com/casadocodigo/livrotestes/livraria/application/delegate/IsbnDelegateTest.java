package br.com.casadocodigo.livrotestes.livraria.application.delegate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.ArrayMatching.asEqualMatchers;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.restassured.http.ContentType;

/**
 * Classe de testa de integrção para a classe de delegate {@link IsbnDelegate}.
 * @author Thiago Leite e Fred Viana
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IsbnDelegateTest {

	/**
	 * URL para a API de ISBN do OpenLibrary.
	 */
	private static final String URL = "https://openlibrary.org/isbn/{NUMBER}.json";

	/**
	 * ISBN de teste.
	 */
	private static final String ISBN = "9788555192135";

	/**
	 * Testa a integração com a API https://openlibrary.org/isbn. Esse teste 
	 * garante o bom funcinamento do método {@link IsbnDelegate#obterLivro(String)}.
	 */
	@Test
	public void testObterLivro() {

		given()
			.pathParam("NUMBER", ISBN)
			.contentType(ContentType.JSON)
            .when().get(URL).then()
	        .assertThat().statusCode(200)
	        			 .contentType(ContentType.JSON)
	                     .body("title",equalTo("Orientação a Objetos: Aprenda seus conceitos e suas aplicabilidades de forma efetiva"))
	                     .body("isbn_13", contains(asEqualMatchers(new String[] {"9788555192135"})));

	}

}
