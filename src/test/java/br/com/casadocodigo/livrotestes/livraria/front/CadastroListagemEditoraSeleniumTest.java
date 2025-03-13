package br.com.casadocodigo.livrotestes.livraria.front;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.com.casadocodigo.livrotestes.livraria.domain.entity.Editora;

/**
 * Classe de teste de sistema para o cadastro e listagem {@link Editora}.
 * 
 * @author Thiago Leite e Fred Viana
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CadastroListagemEditoraSeleniumTest {

	/**
	 * WebDriver do Selenium;
	 */
	private static WebDriver driver;

	/**
	 * Url base do front-end.
	 */
	private static String baseUrl;

	/**
	 * Configurações iniciais.
	 */
	@BeforeAll
	public static void setUp() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");		
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
		
		baseUrl = "http://localhost:4200/";
	}

	/**
	 * Zerando configurações.
	 */
	@AfterAll
	public static void shutDown() {
		
		driver.quit();
		driver = null;
		
		baseUrl = null;
	}

	/**
	 * Teste se o cadastro teve exito, verificando a mensagem de sucesso.
	 */
	@Test
	public void testCadastrar() {

		// Localiza o Selenium na página inicial
		driver.get(baseUrl);
		
		assertTrue(driver.getPageSource().contains("Editora"));

		// Clica na opção do menu de cadastrar editora.
		driver.findElement(By.id("editoraButton")).click();
		driver.findElement(By.id("editoraCadastroButton")).click();

		assertTrue(driver.getPageSource().contains("Cadastro de editora"));
		
		// Preenchendo o nome da editora.
		driver.findElement(By.id("nome")).clear();
		driver.findElement(By.id("nome")).sendKeys("Editora 1 Teste");

		// Preenchendo o cnpj da editora.
		driver.findElement(By.id("cnpj")).clear();
		driver.findElement(By.id("cnpj")).sendKeys("46226436000102");

		// Preenchendo o desconto da editora.
		driver.findElement(By.id("desconto")).clear();
		driver.findElement(By.id("desconto")).sendKeys("20");

		// Executa a ação de salvar
		driver.findElement(By.id("salvar")).click();

		// Verificar mensagem
		String mensagem = driver.findElement(By.xpath("//div[@matsnackbarlabel='']")).getText();
		assertEquals("Editora salva com sucesso.", mensagem);

	}

	/**
	 * Testa se a editora recém cadastra é exibida na tela.
	 */
	@Test
	public void testListar() {

		// Localiza o Selenium na página inicial
		driver.get(baseUrl);

		// Clica na opção do menu de cadastrar editora.
		driver.findElement(By.id("editoraButton")).click();
		driver.findElement(By.id("editoraConsultaButton")).click();

		String cnpj = driver.findElement(By.id("editoraCnpjValue")).getText();

		// Verificar cnpj
		assertEquals("46226436000102", cnpj);

	}

}
