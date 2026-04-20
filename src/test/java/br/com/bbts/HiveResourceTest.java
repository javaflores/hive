package br.com.bbts;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

/**
 * @author Ricardo da Silva Flores (BBTS)
 */
@QuarkusTest
class HiveResourceTest {
	
	@Test
	void testListarDadosClientesComSucesso() {
		
        given()
	        .contentType(ContentType.JSON)
	        .when()
	        .get("/hive/listar/dados/cliente")
	        .then()
	        .log().body()
	        .statusCode(200)
	        .body("[0].nomeEmpresaExterno", is("Shopee LTDA"))
	        .body("[1].nomeEmpresaExterno", is("Mercado Livre do Brasil"));
	}
	
	@Test
	void testListarDadosClientesDoMercadoLivreComSucesso() {
		
        given()
	        .contentType(ContentType.JSON)
	        .when()
	        .get("/hive/listar/dados/cliente/mercadolivre")
	        .then()
	        .log().body()
	        .statusCode(200)
	        .body("[0].nomeEmpresaExterno", is("Mercado Livre do Brasil"));
	}
	
	@Test
	void testListarDadosClientesDaShopeeComSucesso() {
		
        given()
	        .contentType(ContentType.JSON)
	        .when()
	        .get("/hive/listar/dados/cliente/shopee")
	        .then()
	        .log().body()
	        .statusCode(200)
	        .body("[0].nomeEmpresaExterno", is("Shopee LTDA"));
	}

}